package org.deslre.convert;

import org.deslre.entity.po.Persons;
import org.deslre.entity.vo.PersonsVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

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

    Persons convert(PersonsVO vo);

    PersonsVO convert(Persons entity);

    List<PersonsVO> convertList(List<Persons> list);
}
