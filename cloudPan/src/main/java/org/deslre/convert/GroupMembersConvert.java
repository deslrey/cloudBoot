package org.deslre.convert;

import org.deslre.entity.po.GroupMembers;
import org.deslre.entity.vo.GroupMembersVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * ClassName: GroupMembersConvert
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-03 14:17
 * Version: 1.0
 */
@Mapper(componentModel = "spring")
public interface GroupMembersConvert {
    GroupMembersConvert INSTANCE = Mappers.getMapper(GroupMembersConvert.class);

    GroupMembers convert(GroupMembersVO vo);

    GroupMembersVO convert(GroupMembers entity);

    List<GroupMembersVO> convertList(List<GroupMembers> list);
}
