package org.deslre.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ClassName: EntitiesConvert
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-03 14:14
 * Version: 1.0
 */
@Mapper(componentModel = "spring")
public interface EntitiesConvert {

    EntitiesConvert INSTANCE = Mappers.getMapper(EntitiesConvert.class);

}
