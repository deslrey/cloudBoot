package org.deslre.service;

import org.deslre.entity.po.Relationships;
import org.deslre.entity.vo.RelationshipsVO;
import org.deslre.entity.vo.SingleNodeVO;
import org.deslre.result.Results;

import java.util.List;

/**
 * ClassName: RelationshipsService
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:34
 * Version: 1.0
 */
public interface RelationshipsService extends BaseService<Relationships>{
    Results<List<RelationshipsVO>> getGroupRela(Integer id);

    Results<Void> updateNodeData(SingleNodeVO singleNodeVO);
}
