package org.deslre.query;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * ClassName: GroupsQuery
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-03 16:16
 * Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GroupsQuery extends Query {

    private Integer id;

    private String name;

    private String description;

    private Boolean exist;
}
