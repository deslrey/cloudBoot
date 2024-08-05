package org.deslre;

import org.deslre.convert.RelationshipsConvert;
import org.deslre.entity.po.Relationships;
import org.deslre.entity.vo.RelationshipsVO;
import org.deslre.mapper.RelationshipsMapper;
import org.deslre.service.RelationshipsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class CloudBootApplicationTests {

    @Resource
    private RelationshipsMapper relationshipsMapper;

    @Test
    void contextLoads() {
        List<RelationshipsVO> list = relationshipsMapper.findByGroupId(1);
        list.forEach(System.out::println);
    }

}
