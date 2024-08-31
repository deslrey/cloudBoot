package org.deslre;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync // 异步调用
@EnableScheduling // 定时任务
@SpringBootApplication
@EnableTransactionManagement // 事务
@MapperScan("org.deslre.mapper")
public class VueElementAdminBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(VueElementAdminBootApplication.class, args);
    }

}
