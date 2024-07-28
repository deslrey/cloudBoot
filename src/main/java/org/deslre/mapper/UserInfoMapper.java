package org.deslre.mapper;

import org.deslre.entity.po.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * ClassName: UserInfoMapper
 * Description: 用户CRUD
 * Author: Deslrey
 * Date: 2024-07-28 20:43
 * Version: 1.0
 */
public interface UserInfoMapper extends BaseDao<UserInfo> {

    Integer updateUseSpace(@Param("userId") String userId, @Param("useSpace") Long useSpace, @Param("totalSpace") Long totalSpace);
}