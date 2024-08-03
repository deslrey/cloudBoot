package org.deslre;

import org.deslre.convert.RelationshipsConvert;
import org.deslre.entity.po.Relationships;
import org.deslre.entity.vo.RelationshipsVO;
import org.deslre.service.RelationshipsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class CloudBootApplicationTests {

    @Resource
    private RelationshipsService relationshipsService;

    @Test
    void contextLoads() {

        List<Relationships> list = relationshipsService.list();
        List<RelationshipsVO> convertedList = RelationshipsConvert.INSTANCE.convertList(list);
        convertedList.forEach(System.out::println);
    }

}
