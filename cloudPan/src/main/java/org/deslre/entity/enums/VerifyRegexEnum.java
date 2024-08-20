package org.deslre.entity.enums;

import lombok.Getter;

/**
 * ClassName: VerifyRegexEnum
 * Description: 正则校验
 * Author: Deslrey
 * Date: 2024-07-28 20:04
 * Version: 1.0
 */
@Getter
public enum VerifyRegexEnum {
    NO("", "不校验"),
    EMAIL("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$", "邮箱"),
    PASSWORD("^(?=.*\\d)(?=.*[a-zA-Z])[\\da-zA-Z~!@#$%^&*_]{8,}$", "只能是数字，字母，特殊字符 8-18位");

    private final String regex;
    private final String desc;

    VerifyRegexEnum(String regex, String desc) {
        this.regex = regex;
        this.desc = desc;
    }

}
