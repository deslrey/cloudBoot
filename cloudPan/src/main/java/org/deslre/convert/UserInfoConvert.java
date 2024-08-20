package org.deslre.convert;

import org.deslre.entity.po.UserInfo;
import org.deslre.entity.vo.UserInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * ClassName: UserInfoConvert
 * Description: 用户CRUD
 * Author: Deslrey
 * Date: 2024-07-28 20:06
 * Version: 1.0
 */
@Mapper(componentModel = "spring")
public interface UserInfoConvert {
    UserInfoConvert INSTANCE = Mappers.getMapper(UserInfoConvert.class);

    UserInfo convert(UserInfoVO vo);

    UserInfoVO convert(UserInfo entity);

    List<UserInfoVO> convertList(List<UserInfo> list);

}
