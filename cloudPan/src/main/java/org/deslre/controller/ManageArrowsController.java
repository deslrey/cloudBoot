package org.deslre.controller;


import org.deslre.annotation.VerifyParam;
import org.deslre.entity.po.ManageArrows;
import org.deslre.entity.vo.ManageArrowsVO;
import org.deslre.page.PageResult;
import org.deslre.query.ManageArrowsQuery;
import org.deslre.result.Results;
import org.deslre.service.ManageArrowsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author author
 * @since 2024-09-19
 */
@RestController
@RequestMapping("/manageArrows")
public class ManageArrowsController extends BaseController {

    @Resource
    private ManageArrowsService manageArrowsService;

    @PostMapping("getPageData")
    public Results<PageResult<ManageArrowsVO>> getPageData(@Valid ManageArrowsQuery query) {
        return manageArrowsService.getPageData(query);
    }

    @PostMapping("updateArrowsData")
    public Results<Void> updateArrowsData(@VerifyParam(required = true) ManageArrowsVO arrowsVO) {
        System.out.println("arrowsVO = " + arrowsVO);
        return manageArrowsService.updateArrowsData(arrowsVO);
    }

}
