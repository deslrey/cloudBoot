package org.deslre.annotation;

import org.deslre.entity.enums.VerifyRegexEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: VerifyParam
 * Description: 自定义校验参数的范围、必填性和正则匹配规则
 * Author: Deslrey
 * Date: 2024-07-28 20:32
 * Version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
public @interface VerifyParam {

    int min() default -1;

    int max() default -1;

    boolean required() default false;

    VerifyRegexEnum regex() default VerifyRegexEnum.NO;
}

