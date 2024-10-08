package org.deslre.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
/**
 * ClassName: Query
 * Description: 查询公共参数
 * Author: Deslrey
 * Date: 2024-07-28 19:53
 * Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Query {
    @Min(value = 1, message = "页码最小值为 1")
    private Integer pageNo = 1;

    @Range(min = 1, max = 1000, message = "每页条数，取值范围 1-1000")
    private Integer pageSize = 10;

    private int countTotal;

    private int pageTotal;

    private Integer start;

    private Integer end;

    String orderBy;

}