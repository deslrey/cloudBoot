package org.deslre.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: AppConfig
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-07-28 19:15
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
