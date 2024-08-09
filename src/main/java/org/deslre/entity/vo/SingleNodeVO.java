package org.deslre.entity.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * ClassName: SingleNodeVO
 * Description: 单个节点前端接收数据
 * Author: Deslrey
 * Date: 2024-08-09 23:25
 * Version: 1.0
 */
@Data
public class SingleNodeVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer groupId;

    private String nodeType;

    private String name;

    private Integer age;

    private String gender;

    private String birthplace;

    private String idCard;

    private String description;

    private Boolean exist;
}
