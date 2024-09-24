package org.deslre.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.deslre.config.AppConfig;
import org.deslre.convert.UserInfoConvert;
import org.deslre.entity.dto.SessionWebUserDto;
import org.deslre.entity.dto.UserSpaceDto;
import org.deslre.entity.enums.UserStatusEnum;
import org.deslre.entity.po.FileInfo;
import org.deslre.entity.po.UserInfo;
import org.deslre.entity.vo.UserInfoVO;
import org.deslre.exception.DeslreException;
import org.deslre.mapper.FileInfoMapper;
import org.deslre.mapper.UserInfoMapper;
import org.deslre.page.PageResult;
import org.deslre.query.UserInfoQuery;
import org.deslre.result.Constants;
import org.deslre.result.ResultCodeEnum;
import org.deslre.result.Results;
import org.deslre.service.EmailCodeService;
import org.deslre.service.UserInfoService;
import org.deslre.utils.RedisComponent;
import org.deslre.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * ClassName: UserInfoServiceImpl
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-07-28 20:39
 * Version: 1.0
 */
@Service
@AllArgsConstructor
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Resource
    private EmailCodeService emailCodeService;

    @Resource
    private RedisComponent redisComponent;

    @Resource
    private AppConfig appConfig;

    @Resource
    private FileInfoMapper fileInfoMapper;

    @Override
    public PageResult<UserInfoVO> page(UserInfoQuery query) {
        IPage<UserInfo> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getPages(), UserInfoConvert.INSTANCE.convertList(page.getRecords()));
    }

    private QueryWrapper<UserInfo> getWrapper(UserInfoQuery query) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();

        return wrapper;
    }

    @Override
    public void save(UserInfoVO vo) {
        UserInfo entity = UserInfoConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(UserInfoVO vo) {
        UserInfo entity = UserInfoConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(String email, String nickName, String password, String emailCode) {
        UserInfo userInfoEmail = this.getOne(new QueryWrapper<UserInfo>().eq("email", email));
        if (null != userInfoEmail) {
            throw new DeslreException("用户邮箱已注册");
        }

        UserInfo userInfoNickName = this.getOne(new QueryWrapper<UserInfo>().eq("nick_name", nickName));
        if (null != userInfoNickName) {
            throw new DeslreException("用户名已存在");
        }

        //校验邮箱验证码
        emailCodeService.checkCode(email, emailCode);

        String userId = StringUtil.getRandomNumber(Constants.LENGTH_10);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setEmail(email);
        userInfo.setNickName(nickName);
        userInfo.setPassword(StringUtil.encodeByMd5(password));
        userInfo.setJoinTime(new Date());
        userInfo.setStatus(UserStatusEnum.ENABLE.getStatus());
        userInfo.setUseSpace(0L);
        userInfo.setTotalSpace(redisComponent.getSysSettingDto().getUserInitUseSpace() * Constants.MB);
        this.save(userInfo);

    }

    @Override
    public SessionWebUserDto login(String email, String password) {

        UserInfo userInfo = this.getOne(new QueryWrapper<UserInfo>().eq("email", email));

        if (null == userInfo || !userInfo.getPassword().equals(password)) {
            throw new DeslreException("账号或密码错误");
        }

        if (UserStatusEnum.DISABLE.getStatus().equals(userInfo.getStatus())) {
            throw new DeslreException("账号已被禁用");
        }

        UserInfo updateInfo = new UserInfo();
        updateInfo.setLastLoginTime(new Date());
        this.updateById(updateInfo);

        SessionWebUserDto sessionWebUserDto = new SessionWebUserDto();
        sessionWebUserDto.setNickName(userInfo.getNickName());
        sessionWebUserDto.setUserId(userInfo.getUserId());
        sessionWebUserDto.setIsAdmin(ArrayUtils.contains(appConfig.getAdminEmails().split(","), email));
        //用户空间
        UserSpaceDto userSpaceDto = new UserSpaceDto();
        userSpaceDto.setUseSpace(fileInfoMapper.selectUseSpace(userInfo.getUserId()));
        userSpaceDto.setTotalSpace(userInfo.getTotalSpace());
        redisComponent.saveUserUseSpace(userInfo.getUserId(), userSpaceDto);

        return sessionWebUserDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPwd(String email, String password, String emailCode) {

        UserInfo userInfo = this.getOne(new QueryWrapper<UserInfo>().eq("email", email));

        if (null == userInfo) {
            throw new DeslreException("该账号尚未注册");
        }

        emailCodeService.checkCode(email, emailCode);

        userInfo.setPassword(StringUtil.encodeByMd5(password));
        this.updateById(userInfo);
    }

    @Override
    public Results<PageResult<UserInfoVO>> loadUserList(UserInfoQuery userInfoQuery) {
        IPage<UserInfo> page = baseMapper.selectPage(getPage(userInfoQuery), getWrapper(userInfoQuery));

        PageResult<UserInfoVO> pageResult = new PageResult<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getPages(), UserInfoConvert.INSTANCE.convertList(page.getRecords()));
        return Results.ok(pageResult);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Results<Void> updateUserStatus(String userId, Integer status) {
        if (StringUtil.isEmpty(userId)) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }
        if (StringUtil.isNull(status) || !(status == 0 || status == 1)) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }
        UserInfo userInfo = getById(userId);
        if (userInfo == null) {
            throw new DeslreException(ResultCodeEnum.ACCOUNT_ERROR);
        }
        if (ArrayUtils.contains(appConfig.getAdminEmails().split(","), userInfo.getEmail())) {
            throw new DeslreException("不可更改管理员状态");
        }
        if (UserStatusEnum.DISABLE.getStatus().equals(status)) {
            userInfo.setUseSpace(0L);
            userInfo.setStatus(status);
            QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<FileInfo>().eq("user_id", userId);
            fileInfoMapper.delete(queryWrapper);
        }
        updateById(userInfo);

        return Results.ok();
    }
}