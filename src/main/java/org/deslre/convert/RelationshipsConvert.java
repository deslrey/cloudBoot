package org.deslre.convert;

import org.deslre.entity.po.GroupMembers;
import org.deslre.entity.po.Relationships;
import org.deslre.entity.vo.GroupMembersVO;
import org.deslre.entity.vo.RelationshipsVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

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

    Relationships convert(RelationshipsVO vo);

    RelationshipsVO convert(Relationships entity);

    List<RelationshipsVO> convertList(List<Relationships> list);
}
