package org.deslre.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ClassName: PersonsConvert
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-03 14:20
 * Version: 1.0
 */
@Mapper(componentModel = "spring")
public interface PersonsConvert {

    PersonsConvert INSTANCE = Mappers.getMapper(PersonsConvert.class);

}
