package org.deslre;

import org.deslre.convert.RelationshipsConvert;
import org.deslre.entity.po.Relationships;
import org.deslre.entity.vo.PersonsVO;
import org.deslre.entity.vo.RelationshipsVO;
import org.deslre.mapper.RelationshipsMapper;
import org.deslre.service.RelationshipsService;
import org.deslre.utils.StringUtil;
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
        PersonsVO personsVO = new PersonsVO();
        boolean aNull = StringUtil.isNull(personsVO);
        System.out.println("aNull = " + aNull);
    }

}
