package org.deslre.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.deslre.convert.ManageArrowsConvert;
import org.deslre.entity.po.ManageArrows;
import org.deslre.entity.vo.ManageArrowsVO;
import org.deslre.exception.DeslreException;
import org.deslre.mapper.ManageArrowsMapper;
import org.deslre.page.PageResult;
import org.deslre.query.ManageArrowsQuery;
import org.deslre.result.ResultCodeEnum;
import org.deslre.result.Results;
import org.deslre.service.ManageArrowsService;
import org.deslre.utils.StringUtil;
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

    @Override
    public Results<Void> updateArrowsData(ManageArrowsVO arrowsVO) {
        if (StringUtil.isNull(arrowsVO)) {
            throw new DeslreException(ResultCodeEnum.DATA_ERROR);
        }
        if (StringUtil.isNull(arrowsVO.getId()) || arrowsVO.getId() < 0) {
            throw new DeslreException(ResultCodeEnum.DATA_ERROR);
        }
        if (StringUtil.isEmpty(arrowsVO.getArrowName())) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }
        if (StringUtil.isNull(arrowsVO.getExist()) || !arrowsVO.getExist()) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }

        ManageArrows convert = ManageArrowsConvert.INSTANCE.convert(arrowsVO);
        ManageArrows selectArrow = getById(convert.getId());
        if (selectArrow == null) {
            throw new DeslreException(ResultCodeEnum.DATA_ERROR);
        }

        selectArrow.setArrowName(convert.getArrowName());

        boolean updateData = updateById(selectArrow);
        if (updateData) {
            return Results.ok("修改成功");
        }
        return Results.fail("修改失败");
    }

    @Override
    public Results<Void> addArrowsData(String arrowName, String nickName) {
        if (StringUtil.isEmpty(arrowName)) {
            throw new DeslreException(ResultCodeEnum.EMPTY_VALUE);
        }
        if (StringUtil.isEmpty(nickName)) {
            throw new DeslreException("用户验证失败,请检查是否登录");
        }

        ManageArrows manageArrows = new ManageArrows();
        manageArrows.setArrowName(arrowName);
        manageArrows.setCreateUser(nickName);
        manageArrows.setExist(true);

        boolean saved = save(manageArrows);
        if (saved) {
            return Results.ok("添加成功");
        }
        return Results.fail("添加失败");

    }

}
