package org.deslre.entity.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * ClassName: Groups
 * Description: GroupsVO
 * Author: Deslrey
 * Date: 2024-08-01 23:16
 * Version: 1.0
 */
@Data
public class GroupsVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String description;

    private Boolean exist;


}
