package org.deslre.entity.dto;


import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * ClassName: SessionWebUserDto
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-07-28 19:30
 * Version: 1.0
 */
@Data
public class SessionWebUserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String nickName;

    private String userId;

    private Boolean isAdmin;

    private String avatar;

}
