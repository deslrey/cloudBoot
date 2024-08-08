package org.deslre.controller;

import org.deslre.entity.vo.PersonsVO;
import org.deslre.result.Results;
import org.deslre.service.PersonsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ClassName: PersonsController
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:23
 * Version: 1.0
 */
@RestController
@RequestMapping("persons")
public class PersonsController extends BaseController {

    @Resource
    private PersonsService personsService;


    @PostMapping("updatePerson")
    public Results<Void> updatePerson(PersonsVO personsVO, @RequestParam("groupsId") Integer groupId) {
        System.out.println("personsVO = " + personsVO);
        System.out.println("groupId = " + groupId);
        return personsService.updatePerson(personsVO,groupId);
    }

}
