package org.deslre.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.deslre.convert.ManageArrowsConvert;
import org.deslre.entity.po.ManageArrows;
import org.deslre.entity.vo.ManageArrowsVO;
import org.deslre.mapper.ManageArrowsMapper;
import org.deslre.page.PageResult;
import org.deslre.query.ManageArrowsQuery;
import org.deslre.result.Results;
import org.deslre.service.ManageArrowsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author author
 * @since 2024-09-19
 */
@Service
public class ManageArrowsServiceImpl extends BaseServiceImpl<ManageArrowsMapper, ManageArrows> implements ManageArrowsService {

    @Override
    public Results<PageResult<ManageArrowsVO>> getPageData(ManageArrowsQuery query) {
        IPage<ManageArrows> page = baseMapper.selectPage(getPage(query), getWrapper(new ManageArrowsQuery()));

        List<ManageArrows> records = page.getRecords();
        records.forEach(System.out::println);

        PageResult<ManageArrowsVO> pageResult = new PageResult<>(page.getTotal(), page.getSize(), page.getPages(), ManageArrowsConvert.INSTANCE.convertList(page.getRecords()));
        return Results.ok(pageResult);
    }
}
