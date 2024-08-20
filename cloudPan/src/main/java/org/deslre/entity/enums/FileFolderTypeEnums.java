package org.deslre.entity.enums;

import lombok.Getter;

/**
 * ClassName: FileFolderTypeEnums
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-07-28 20:00
 * Version: 1.0
 */
@Getter
public enum FileFolderTypeEnums {
    FILE(0, "文件"),
    FOLDER(1, "目录");

    private final Integer type;
    private final String desc;

    FileFolderTypeEnums(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

}