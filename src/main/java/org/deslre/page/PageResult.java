package org.deslre.page;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * ClassName: PageResult
 * Description: 分页工具类
 * Author: Deslrey
 * Date: 2024-07-28 19:52
 * Version: 1.0
 */
@Data
public class PageResult<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    // 总记录数
    private Integer totalCount;
    private Integer pageSize;
    private Integer pageNo;
    private Integer pageTotal;

    // 列表数据
    private List<T> list;

    /**
     * 分页
     * @param list   列表数据
     * @param totalCount  总记录数
     */
    public PageResult(List<T> list, long totalCount) {
        this.list = list;
        this.totalCount = (int)totalCount;
    }

    public PageResult(long totalCount, long pageSize, long pageNo, List<T> list) {
        this.totalCount = (int)totalCount;
        this.pageSize = (int)pageSize;
        this.pageNo = (int)pageNo;
        this.list = list;
    }

    public PageResult(long totalCount, long pageSize, long pageNo, long pageTotal, List<T> list) {
        if (pageNo == 0) {
            pageNo = 1;
        }
        this.totalCount = (int)totalCount;
        this.pageSize = (int)pageSize;
        this.pageNo = (int)pageNo;
        this.pageTotal = (int)pageTotal;
        this.list = list;
    }

    public PageResult(List<T> list) {
        this.list = list;
    }

    public PageResult() {

    }
}