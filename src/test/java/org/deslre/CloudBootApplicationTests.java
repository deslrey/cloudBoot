package org.deslre;

import org.deslre.service.RelationshipsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class CloudBootApplicationTests {

    @Resource
    private RelationshipsService relationshipsService;

    @Test
    void contextLoads() {

        relationshipsService.list().forEach(System.out::println);

    }

}
