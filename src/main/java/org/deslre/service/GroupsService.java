package org.deslre.service;

import org.deslre.entity.po.Groups;
import org.deslre.result.Results;

import java.util.List;

/**
 * ClassName: GroupsService
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:33
 * Version: 1.0
 */
public interface GroupsService extends BaseService<Groups>{
    Results<List<Groups>> getAllGroups();
}
