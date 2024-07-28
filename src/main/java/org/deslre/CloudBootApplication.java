package org.deslre;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.deslre.mapper")
public class CloudBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudBootApplication.class, args);
    }

}
