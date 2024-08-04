package org.deslre.service;

import org.deslre.entity.po.Groups;
import org.deslre.entity.vo.GroupsVO;
import org.deslre.page.PageResult;
import org.deslre.query.GroupsQuery;
import org.deslre.result.Results;

import javax.validation.Valid;
import java.util.List;

/**
 * ClassName: GroupsService
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:33
 * Version: 1.0
 */
public interface GroupsService extends BaseService<Groups> {
    Results<PageResult<GroupsVO>> getAllGroups(GroupsQuery query);

    Results<Void> deleteGroup(Integer id);

    Results<Void> updateGroup(GroupsVO groupsVO);
}
