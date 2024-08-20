package org.deslre.entity.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: UserStatusEnum
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-07-28 20:02
 * Version: 1.0
 */
@Getter
public enum UserStatusEnum {

    DISABLE(0, "禁用"),
    ENABLE(1, "启用");


    private final Integer status;
    @Setter
    private final String desc;

    UserStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static UserStatusEnum getByStatus(Integer status) {
        for (UserStatusEnum item : UserStatusEnum.values()) {
            if (item.getStatus().equals(status)) {
                return item;
            }
        }
        return null;
    }

}
