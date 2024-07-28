package org.deslre.controller;

import org.deslre.annotation.GlobalInterceptor;
import org.deslre.annotation.VerifyParam;
import org.deslre.config.AppConfig;
import org.deslre.entity.dto.CreateImageCode;
import org.deslre.entity.dto.SessionWebUserDto;
import org.deslre.entity.dto.UserSpaceDto;
import org.deslre.entity.enums.VerifyRegexEnum;
import org.deslre.entity.po.UserInfo;
import org.deslre.exception.DeslreException;
import org.deslre.result.Constants;
import org.deslre.result.Results;
import org.deslre.service.UserInfoService;
import org.deslre.utils.RedisComponent;
import org.deslre.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * ClassName: UserInfoController
 * Description: 用户CRUD
 * Author: Deslrey
 * Date: 2024-07-28 20:29
 * Version: 1.0
 */
@RestController
@RequestMapping("userInfo")
public class UserInfoController extends BaseController {
    @Resource
    private UserInfoService userInfoService;

    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Resource
    private AppConfig appConfig;

    @Resource
    private RedisComponent redisComponent;

    /**
     * 根据请求类型返回验证码并存入session
     *
     * @param type 0:登录注册  1:邮箱验证码发送  默认0
     */
    @RequestMapping("/checkCode")
    public void checkCode(HttpServletResponse response, HttpSession session, Integer type) throws IOException {
        CreateImageCode vCode = new CreateImageCode(130, 38, 5, 10);
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        String code = vCode.getCode();

        System.err.println("登录验证码  ------------ >  " + code);

        if (type == null || type == 0) {
            session.setAttribute(Constants.CHECK_CODE_KEY, code);
        } else {
            session.setAttribute(Constants.CHECK_CODE_KEY_EMAIL, code);
        }
        vCode.write(response.getOutputStream());
    }

    @RequestMapping("/register")
    @GlobalInterceptor(checkParams = true, checkLogin = false)
    public Results<String> register(HttpSession session,
                                    @VerifyParam(required = true, regex = VerifyRegexEnum.EMAIL, max = 150) String email,
                                    @VerifyParam(required = true) String nickName,
                                    @VerifyParam(required = true, regex = VerifyRegexEnum.PASSWORD, min = 8) String password,
                                    @VerifyParam(required = true) String checkCode,
                                    @VerifyParam(required = true) String emailCode) {
        try {
            if (!checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY))) {
                throw new DeslreException("图片验证码不正确");
            }
            userInfoService.register(email, nickName, password, emailCode);
            return Results.ok("邮件发送成功");
        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY);
        }
    }

    @RequestMapping("/login")
    @GlobalInterceptor(checkParams = true, checkLogin = false)
    public Results<SessionWebUserDto> login(HttpSession session,
                                            @VerifyParam(required = true) String email,
                                            @VerifyParam(required = true) String password,
                                            @VerifyParam(required = true) String checkCode) {
        try {
            if (!checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY))) {
                throw new DeslreException("图片验证码不正确");
            }
            SessionWebUserDto sessionWebUserDto = userInfoService.login(email, password);
            session.setAttribute(Constants.SESSION_KEY, sessionWebUserDto);
            return Results.ok(sessionWebUserDto);
        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY);
        }
    }


    @RequestMapping("/resetPwd")
    @GlobalInterceptor(checkParams = true, checkLogin = false)
    public Results<String> resetPwd(HttpSession session,
                                    @VerifyParam(required = true, regex = VerifyRegexEnum.EMAIL, max = 150) String email,
                                    @VerifyParam(required = true, regex = VerifyRegexEnum.PASSWORD, min = 8, max = 10) String password,
                                    @VerifyParam(required = true) String checkCode,
                                    @VerifyParam(required = true) String emailCode) {
        try {
            if (!checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY))) {
                throw new DeslreException("图片验证码不正确");
            }
            userInfoService.resetPwd(email, password, emailCode);
            return Results.ok("密码修改成功");
        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY);
        }
    }

    @RequestMapping("/updatePassword")
    @GlobalInterceptor(checkParams = true)
    public Results<Object> updatePassword(HttpSession session,
                                          @VerifyParam(required = true, regex = VerifyRegexEnum.PASSWORD, min = 8, max = 18) String password) {
        SessionWebUserDto sessionWebUserDto = getUserInfoFromSession(session);
        UserInfo userInfo = userInfoService.getById(sessionWebUserDto.getUserId());
        userInfo.setPassword(StringUtil.encodeByMd5(password));
        userInfoService.updateById(userInfo);
        return Results.ok(null);
    }

    @RequestMapping("/getUserInfo")
    @GlobalInterceptor(checkParams = true)
    public Results<SessionWebUserDto> getUserInfo(HttpSession session) {
        SessionWebUserDto sessionWebUserDto = getUserInfoFromSession(session);

        return Results.ok(sessionWebUserDto);
    }

    @RequestMapping("/getUseSpace")
    @GlobalInterceptor(checkParams = true)
    public Results<UserSpaceDto> getUseSpace(HttpSession session) {
        SessionWebUserDto sessionWebUserDto = getUserInfoFromSession(session);

        UserSpaceDto userUseSpace = redisComponent.getUserUseSpace(sessionWebUserDto.getUserId());
        return Results.ok(userUseSpace);
    }

    @RequestMapping("/logout")
    public Results<Object> logout(HttpSession session) {
        session.invalidate();
        return Results.ok(null);
    }


    /**
     * 获取用户头像
     *
     * @return void
     */
    @RequestMapping("/getAvatar/{userId}")
    @GlobalInterceptor(checkParams = true, checkLogin = false)
    public void getAvatar(HttpServletResponse response, HttpSession session, @VerifyParam(required = true) @PathVariable("userId") String userId) {
        String avatarFolderName = Constants.FILE_FOLDER_FILE + Constants.FILE_FOLDER_AVATAR_NAME;
        File folder = new File(appConfig.getProjectFolder() + avatarFolderName);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String avatarPath = appConfig.getProjectFolder() + avatarFolderName + userId + Constants.AVATAR_SUFFIX;
        File file = new File(avatarPath);
        if (!file.exists()) {
            if (!new File(appConfig.getProjectFolder() + avatarFolderName + Constants.AVATAR_DEFAULT).exists()) {
                printNoDefaultImage(response);
            }
            avatarPath = appConfig.getProjectFolder() + avatarFolderName + Constants.AVATAR_DEFAULT;
        }

        response.setContentType("image/jpg");
        readFile(response, avatarPath);
    }

    @RequestMapping("/updateUserAvatar")
    @GlobalInterceptor
    public Results<Object> updateUserAvatar(HttpSession session, MultipartFile avatar) {

        SessionWebUserDto webUserDto = getUserInfoFromSession(session);
        // 得到头像文件夹
        String baseFolder = appConfig.getProjectFolder() + Constants.FILE_FOLDER_FILE;
        File targetFileFolder = new File(baseFolder + Constants.FILE_FOLDER_AVATAR_NAME);
        // 如果不存在就创建
        if (!targetFileFolder.exists()) {
            targetFileFolder.mkdirs();
        }
        // 得到新头像绝对路径
        File targetFile = new File(targetFileFolder.getPath() + "/" + webUserDto.getUserId() + Constants.AVATAR_SUFFIX);
        try {
            // 输出
            avatar.transferTo(targetFile);
        } catch (Exception e) {
            logger.error("上传头像失败", e);
        }

        // 同时将数据库中qq头像设为空
        UserInfo userInfo = userInfoService.getById(webUserDto.getUserId());
        userInfo.setQqAvatar("");
        userInfoService.updateById(userInfo);
        webUserDto.setAvatar(null);
        //更新session
        session.setAttribute(Constants.SESSION_KEY, webUserDto);
        return Results.ok(null);
    }
}
