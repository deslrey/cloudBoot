package org.deslre.mapper;

import org.deslre.entity.po.Relationships;
import org.deslre.entity.vo.RelationshipsVO;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * ClassName: RelationshipsMapper
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:27
 * Version: 1.0
 */
public interface RelationshipsMapper extends BaseDao<Relationships> {
    List<RelationshipsVO> findByGroupId(@Param("groupId") Integer groupId);

}
