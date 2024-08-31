package org.deslre.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.ArrayUtils;
import org.deslre.config.AppConfig;
import org.deslre.entity.dto.SessionWebUserDto;
import org.deslre.entity.enums.UserStatusEnum;
import org.deslre.entity.po.UserInfo;
import org.deslre.exception.DeslreException;
import org.deslre.mapper.UserInfoMapper;
import org.deslre.result.Constants;
import org.deslre.service.UserInfoService;
import org.deslre.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * ClassName: UserInfoServiceImpl
 * Description: 用户相关业务逻辑操作
 * Author: Deslrey
 * Date: 2024-08-31 21:03
 * Version: 1.0
 */
@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Resource
    private AppConfig appConfig;

    @Override
    public SessionWebUserDto login(String email, String password) {

        if (StringUtil.isEmpty(email) || StringUtil.isEmpty(password)) {
            throw new DeslreException("账号或密码不能为空");
        }

        String newPassword = Constants.NEW_START_PASSWORD + password;

        UserInfo userInfo = this.getOne(new QueryWrapper<UserInfo>().eq("email", email));

        if (userInfo == null || (StringUtil.isNotEmpty(userInfo.getPassword()) && !userInfo.getPassword().equals(newPassword))) {
            throw new DeslreException("账号或密码错误");
        }

        if (UserStatusEnum.DISABLE.getStatus().equals(userInfo.getStatus())) {
            throw new DeslreException("账号已被禁用");
        }

        userInfo.setLastLoginTime(new Date());
        this.updateById(userInfo);

        SessionWebUserDto sessionWebUserDto = new SessionWebUserDto();
        sessionWebUserDto.setNickName(userInfo.getNickName());
        sessionWebUserDto.setUserId(userInfo.getUserId());
        sessionWebUserDto.setIsAdmin(ArrayUtils.contains(appConfig.getAdminEmails().split(","), email));
//        //用户空间
//        UserSpaceDto userSpaceDto = new UserSpaceDto();
//        userSpaceDto.setUseSpace(fileInfoMapper.selectUseSpace(userInfo.getUserId()));
//        userSpaceDto.setTotalSpace(userInfo.getTotalSpace());
//        redisComponent.saveUserUseSpace(userInfo.getUserId(), userSpaceDto);

        return sessionWebUserDto;
    }
}
