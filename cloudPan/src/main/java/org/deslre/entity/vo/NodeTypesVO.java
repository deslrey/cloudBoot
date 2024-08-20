package org.deslre.entity.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * ClassName: NodeTypesVO
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:17
 * Version: 1.0
 */
@Data
public class NodeTypesVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String typeName;

    private Boolean exist;


}