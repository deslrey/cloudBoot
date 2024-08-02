package org.deslre.controller;

import org.deslre.entity.po.Groups;
import org.deslre.result.Results;
import org.deslre.service.GroupsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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

    @GetMapping("getAllGroups")
    public Results<List<Groups>> getAllGroups(HttpSession session) {
        return groupsService.getAllGroups();
    }

}
