package org.deslre.service;

import org.deslre.entity.po.GroupMembers;
import org.deslre.entity.vo.SingleNodeVO;
import org.deslre.result.Results;

/**
 * ClassName: GroupMembersService
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:28
 * Version: 1.0
 */
public interface GroupMembersService extends BaseService<GroupMembers>{
    Results<Void> updateNodeData(SingleNodeVO singleNode);
}
