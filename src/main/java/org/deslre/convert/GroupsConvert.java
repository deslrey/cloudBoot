package org.deslre.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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

}
