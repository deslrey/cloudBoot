package org.deslre.entity.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * ClassName: GroupMembersVO
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:16
 * Version: 1.0
 */
@Data
public class GroupMembersVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer groupId;

    private Integer nodeId;

    private String nodeType;

    private String role;

    private Boolean exist;
}
