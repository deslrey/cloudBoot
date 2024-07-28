package org.deslre.entity.enums;

import lombok.Getter;

/**
 * ClassName: DateTimePatternEnum
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-07-28 19:58
 * Version: 1.0
 */
@Getter
public enum DateTimePatternEnum {
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"), YYYY_MM_DD("yyyy-MM-dd"), YYYYMM("yyyyMM");

    private final String pattern;

    DateTimePatternEnum(String pattern) {
        this.pattern = pattern;
    }

}
