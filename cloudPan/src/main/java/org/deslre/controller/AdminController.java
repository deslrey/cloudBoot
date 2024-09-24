package org.deslre.controller;

import org.deslre.annotation.GlobalInterceptor;
import org.deslre.annotation.VerifyParam;
import org.deslre.entity.dto.SysSettingDto;
import org.deslre.entity.vo.FileInfoVO;
import org.deslre.entity.vo.UserInfoVO;
import org.deslre.page.PageResult;
import org.deslre.query.FileInfoQuery;
import org.deslre.query.UserInfoQuery;
import org.deslre.result.Results;
import org.deslre.service.FileInfoService;
import org.deslre.service.UserInfoService;
import org.deslre.utils.RedisComponent;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName: AdminController
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-09-24 11:58
 * Version: 1.0
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {
    @Resource
    private RedisComponent redisComponent;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private FileInfoService fileInfoService;

    @RequestMapping("/getSysSettings")
    @GlobalInterceptor(checkParams = true, checkAdmin = true)
    public Results<SysSettingDto> getSysSettings() {
        return Results.ok(redisComponent.getSysSettingDto());
    }


    @RequestMapping("/saveSysSettings")
    @GlobalInterceptor(checkParams = true, checkAdmin = true)
    public Results<Void> saveSysSettings(
            @VerifyParam(required = true) String registerEmailTitle,
            @VerifyParam(required = true) String registerEmailContent,
            @VerifyParam(required = true) Integer userInitUseSpace) {
        SysSettingDto sysSettingsDto = new SysSettingDto();
        sysSettingsDto.setRegisterEmailTitle(registerEmailTitle);
        sysSettingsDto.setRegisterEmailContent(registerEmailContent);
        sysSettingsDto.setUserInitUseSpace(userInitUseSpace);
        redisComponent.saveSysSettingsDto(sysSettingsDto);
        return Results.ok(null);
    }

    @PostMapping("loadUserList")
    @GlobalInterceptor(checkAdmin = true, checkParams = true)
    public Results<PageResult<UserInfoVO>> loadUserList(UserInfoQuery userInfoQuery) {
        return userInfoService.loadUserList(userInfoQuery);
    }

    @PostMapping("/getFolderInfo")
    @GlobalInterceptor(checkLogin = false, checkAdmin = true, checkParams = true)
    public Results<List<FileInfoVO>> getFolderInfo(@VerifyParam(required = true) String path) {
        return super.getFolderInfo(path, null);
    }


    @PostMapping("/getFile/{userId}/{fileId}")
    @GlobalInterceptor(checkParams = true, checkAdmin = true)
    public void getFile(HttpServletResponse response,
                        @PathVariable("userId") @VerifyParam(required = true) String userId,
                        @PathVariable("fileId") @VerifyParam(required = true) String fileId) {
        super.getFile(response, fileId, userId);
    }


    @PostMapping("/ts/getVideoInfo/{userId}/{fileId}")
    @GlobalInterceptor(checkParams = true, checkAdmin = true)
    public void getVideoInfo(HttpServletResponse response,
                             @PathVariable("userId") @VerifyParam(required = true) String userId,
                             @PathVariable("fileId") @VerifyParam(required = true) String fileId) {
        super.getFile(response, fileId, userId);
    }

    @PostMapping("/createDownloadUrl/{userId}/{fileId}")
    @GlobalInterceptor(checkParams = true, checkAdmin = true)
    public Results<String> createDownloadUrl(@PathVariable("userId") @VerifyParam(required = true) String userId,
                                             @PathVariable("fileId") @VerifyParam(required = true) String fileId) {
        return super.createDownloadUrl(fileId, userId);
    }

    /**
     * 下载
     */
    @PostMapping("/download/{code}")
    @GlobalInterceptor(checkLogin = false, checkParams = true)
    public void download(HttpServletRequest request, HttpServletResponse response,
                         @PathVariable("code") @VerifyParam(required = true) String code) throws Exception {
        super.download(request, response, code);
    }


    @PostMapping("/delFile")
    @GlobalInterceptor(checkParams = true, checkAdmin = true)
    public Results<Void> delFile(@VerifyParam(required = true) String fileIdAndUserIds) {
        String[] fileIdAndUserIdArray = fileIdAndUserIds.split(",");
        for (String fileIdAndUserId : fileIdAndUserIdArray) {
            String[] itemArray = fileIdAndUserId.split("_");
            fileInfoService.delFileBatch(itemArray[0], itemArray[1], true);
        }
        return Results.ok();
    }

    @PostMapping("updateUserStatus")
    @GlobalInterceptor(checkParams = true, checkAdmin = true)
    public Results<Void> updateUserStatus(@VerifyParam(required = true) String userId,
                                          @VerifyParam(required = true) Integer status) {
        return userInfoService.updateUserStatus(userId, status);
    }

    @PostMapping("loadFileList")
    @GlobalInterceptor(checkParams = true, checkAdmin = true)
    public Results<PageResult<FileInfoVO>> loadFileList(FileInfoQuery query) {
        System.out.println("query = " + query);
        query.setOrderBy("last_update_time desc");
        query.setQueryNickName(true);
        return fileInfoService.loadFileList(query);
    }

}
