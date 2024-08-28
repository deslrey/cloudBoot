package org.deslre.controller;

import org.deslre.entity.vo.EntitiesVO;
import org.deslre.result.Results;
import org.deslre.service.EntitiesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ClassName: EntitiesController
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:22
 * Version: 1.0
 */
@RestController
@RequestMapping("entities")
public class EntitiesController extends BaseController {

    @Resource
    private EntitiesService entitiesService;

    @PostMapping("updateEntities")
    public Results<Void> updateEntities(EntitiesVO entitiesVO, @RequestParam("groupsId") Integer groupId) {
        System.out.println("entitiesVO = " + entitiesVO);
        System.out.println("groupId = " + groupId);
        return entitiesService.updateEntities(entitiesVO, groupId);
    }

}
