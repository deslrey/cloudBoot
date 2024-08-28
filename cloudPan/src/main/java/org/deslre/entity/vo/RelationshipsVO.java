package org.deslre.entity.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * ClassName: Relationships
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:20
 * Version: 1.0
 */
@Data
public class RelationshipsVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long relationId;

    private Long startId;

    private String startName;

    private String startType;

    private String information;

    private Long endId;

    private String endName;

    private String endType;

    private Long groupId;

    private Boolean exist;

}
