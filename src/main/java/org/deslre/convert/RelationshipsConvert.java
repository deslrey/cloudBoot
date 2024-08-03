package org.deslre.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ClassName: RelationshipsConvert
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-03 14:20
 * Version: 1.0
 */
@Mapper(componentModel = "spring")
public interface RelationshipsConvert {

    RelationshipsConvert INSTANCE = Mappers.getMapper(RelationshipsConvert.class);

}
