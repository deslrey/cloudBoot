package org.deslre.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * ClassName: ManageArrowsQuery
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-09-19 9:32
 * Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ManageArrowsQuery extends Query {

    private Integer id;

    private String arrowName;

    private Date createTime;

    private Date updateTime;

    private Boolean exist;

    private String createUser;
}
