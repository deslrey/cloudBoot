package org.deslre.convert;

import org.deslre.entity.po.Groups;
import org.deslre.entity.vo.GroupsVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * ClassName: GroupsConvert
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-03 14:18
 * Version: 1.0
 */
@Mapper(componentModel = "spring")
public interface GroupsConvert {

    GroupsConvert INSTANCE = Mappers.getMapper(GroupsConvert.class);

    Groups convert(GroupsVO vo);

    GroupsVO convert(Groups entity);

    List<GroupsVO> convertList(List<Groups> list);
}
