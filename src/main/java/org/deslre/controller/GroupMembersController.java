package org.deslre.controller;

import org.deslre.annotation.VerifyParam;
import org.deslre.entity.vo.SingleNodeVO;
import org.deslre.result.Results;
import org.deslre.service.GroupMembersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * ClassName: GroupMembersController
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:22
 * Version: 1.0
 */
@RestController
@RequestMapping("groupMembers")
public class GroupMembersController extends BaseController {

    @Resource
    private GroupMembersService groupMembersService;

    @PostMapping("updateNodeData")
    public Results<Void> updateNodeData(SingleNodeVO singleNode) {
        System.out.println("singleNode = " + singleNode);
        return Results.ok("更新完成");
//        return groupMembersService.updateNodeData(singleNode);
    }

    @PostMapping("getAllData")
    public Results<Map<String, List<SingleNodeVO>>> getAllData(@VerifyParam(required = true) @RequestParam("groupsId") Integer id) {
        return groupMembersService.getAllData(id);
    }
}
