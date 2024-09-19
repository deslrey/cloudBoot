package org.deslre.convert;

import org.deslre.entity.po.ManageArrows;
import org.deslre.entity.vo.ManageArrowsVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * ClassName: ManageArrowsConvert
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-09-19 9:28
 * Version: 1.0
 */
@Mapper(componentModel = "spring")
public interface ManageArrowsConvert {

    ManageArrowsConvert INSTANCE = Mappers.getMapper(ManageArrowsConvert.class);
    ManageArrows convert(ManageArrowsVO vo);

    ManageArrowsVO convert(ManageArrows entity);

    List<ManageArrowsVO> convertList(List<ManageArrows> list);
}
