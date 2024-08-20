package org.deslre.entity.enums;

import lombok.Getter;

/**
 * ClassName: UploadStatusEnums
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-07-28 20:02
 * Version: 1.0
 */
@Getter
public enum UploadStatusEnums {
    UPLOAD_SECONDS("upload_seconds", "秒传"),
    UPLOADING("uploading", "上传中"),
    UPLOAD_FINISH("upload_finish", "上传完成");

    private final String code;
    private final String desc;

    UploadStatusEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}

