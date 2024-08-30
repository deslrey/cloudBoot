package org.deslre.entity.enums;

import lombok.Getter;

/**
 * ClassName: FileStatusEnums
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-07-28 20:01
 * Version: 1.0
 */
@Getter
public enum FileStatusEnums {
    TRANSFER(0, "转码中"),
    TRANSFER_FAIL(1, "转码失败"),
    USING(2, "使用中");

    private final Integer status;
    private final String desc;

    FileStatusEnums(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }


}
