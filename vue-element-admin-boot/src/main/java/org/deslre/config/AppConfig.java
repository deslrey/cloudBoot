package org.deslre.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: AppConfig
 * Description: 管理员账号及存储文件地址设置
 * Author: Deslrey
 * Date: 2024-08-31 21:39
 * Version: 1.0
 */
@Data
@Configuration
public class AppConfig {

    @Value("${admin.emails}")
    public String adminEmails;

    @Value("${project.folder}")
    private String projectFolder;

}

