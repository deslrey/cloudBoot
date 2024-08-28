package org.deslre.convert;

import org.deslre.entity.po.Entities;
import org.deslre.entity.po.Persons;
import org.deslre.entity.vo.EntitiesVO;
import org.deslre.entity.vo.SingleNodeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

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

    Entities convert(EntitiesVO vo);

    Entities convert(SingleNodeVO vo);

    EntitiesVO convert(Entities entity);

    List<EntitiesVO> convertList(List<Entities> list);
}
