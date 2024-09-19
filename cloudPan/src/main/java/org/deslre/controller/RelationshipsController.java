package org.deslre.controller;

import org.deslre.annotation.VerifyParam;
import org.deslre.entity.vo.RelationshipsVO;
import org.deslre.entity.vo.SingleNodeVO;
import org.deslre.result.Results;
import org.deslre.service.RelationshipsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: RelationshipsController
 * Description: 节点关系请求层
 * Author: Deslrey
 * Date: 2024-08-01 23:23
 * Version: 1.0
 */
@RestController
@RequestMapping("relationships")
public class RelationshipsController extends BaseController {

    @Resource
    private RelationshipsService relationshipsService;

    @PostMapping("getGroupRela")
    public Results<List<RelationshipsVO>> getGroupRela(@VerifyParam(required = true) @RequestParam("groupId") Integer id) {
        return relationshipsService.getGroupRela(id);
    }

    @PostMapping("updateNodeData")
    public Results<Void> updateNodeData(SingleNodeVO singleNodeVO) {
        System.out.println("singleNodeVO = " + singleNodeVO);
        return relationshipsService.updateNodeData(singleNodeVO);
    }
}
