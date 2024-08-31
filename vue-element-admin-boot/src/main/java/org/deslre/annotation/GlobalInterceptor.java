package org.deslre.annotation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * ClassName: GlobalInterceptor
 * Description: 全局拦截器注解
 * Author: Deslrey
 * Date: 2024-07-28 20:31
 * Version: 1.0
 */
@Mapping
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GlobalInterceptor {

    /**
     * 校验参数
     *
     * @return boolean
     */
    boolean checkParams() default false;

    /**
     * 校验登录
     *
     * @return boolean
     */
    boolean checkLogin() default true;

    /**
     * 校验超级管理员
     *
     * @return boolean
     */
    boolean checkAdmin() default false;

}

