package org.deslre.controller;

import org.deslre.annotation.VerifyParam;
import org.deslre.entity.vo.GroupsVO;
import org.deslre.page.PageResult;
import org.deslre.query.FileInfoQuery;
import org.deslre.query.GroupsQuery;
import org.deslre.result.Results;
import org.deslre.service.GroupsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * ClassName: GroupsController
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:22
 * Version: 1.0
 */
@RestController
@RequestMapping("groups")
public class GroupsController extends BaseController {

    @Resource
    private GroupsService groupsService;

    @PostMapping("getAllGroups")
    public Results<PageResult<GroupsVO>> getAllGroups(@Valid GroupsQuery query) {
        return groupsService.getAllGroups(query);
    }

    @PostMapping("deleteGroup")
    public Results<Void> deleteGroup(@VerifyParam(required = true) @RequestParam("groupsId") Integer id) {
        return groupsService.deleteGroup(id);
    }

    @PostMapping("updateGroup")
    public Results<Void> updateGroup(GroupsVO groupsVO) {
        return groupsService.updateGroup(groupsVO);
    }

}
