package org.deslre;

import org.deslre.convert.RelationshipsConvert;
import org.deslre.entity.po.ManageArrows;
import org.deslre.entity.po.Relationships;
import org.deslre.entity.vo.PersonsVO;
import org.deslre.entity.vo.RelationshipsVO;
import org.deslre.mapper.RelationshipsMapper;
import org.deslre.service.ManageArrowsService;
import org.deslre.service.RelationshipsService;
import org.deslre.utils.DateUtils;
import org.deslre.utils.StringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
class CloudBootApplicationTests {

    @Resource
    private RelationshipsMapper relationshipsMapper;
    @Resource
    private ManageArrowsService manageArrowsService;

    @Test
    void contextLoads() {
        ManageArrows manageArrows = manageArrowsService.getById(4);
        manageArrows.setArrowName("1");
        manageArrowsService.updateById(manageArrows);
//        manageArrows.setArrowName("李四");
//        manageArrows.setExist(true);
//        manageArrowsService.save(manageArrows);
    }

}
