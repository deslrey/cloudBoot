package org.deslre.service;

import org.deslre.entity.po.Entities;
import org.deslre.entity.vo.EntitiesVO;
import org.deslre.result.Results;

/**
 * ClassName: EntitiesService
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:28
 * Version: 1.0
 */
public interface EntitiesService extends BaseService<Entities>{
    Results<Void> updateEntities(EntitiesVO entitiesVO, Integer groupId);
}
