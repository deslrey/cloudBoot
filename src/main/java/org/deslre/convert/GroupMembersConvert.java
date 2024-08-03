package org.deslre.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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
}
