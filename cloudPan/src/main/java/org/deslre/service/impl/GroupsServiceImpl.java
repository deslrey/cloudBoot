package org.deslre.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.deslre.convert.FileInfoConvert;
import org.deslre.convert.GroupsConvert;
import org.deslre.entity.po.FileInfo;
import org.deslre.entity.po.Groups;
import org.deslre.entity.vo.GroupsVO;
import org.deslre.exception.DeslreException;
import org.deslre.mapper.GroupsMapper;
import org.deslre.page.PageResult;
import org.deslre.query.FileInfoQuery;
import org.deslre.query.GroupsQuery;
import org.deslre.result.ResultCodeEnum;
import org.deslre.result.Results;
import org.deslre.service.GroupsService;
import org.deslre.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

/**
 * ClassName: GroupsServiceImpl
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:38
 * Version: 1.0
 */
@Service
public class GroupsServiceImpl extends BaseServiceImpl<GroupsMapper, Groups> implements GroupsService {
    @Override
    public Results<PageResult<GroupsVO>> getAllGroups(GroupsQuery query) {
        IPage<Groups> page = baseMapper.selectPage(getPage(query), getWrapper(new GroupsQuery()));
        PageResult<GroupsVO> pageResult = new PageResult<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getPages(), GroupsConvert.INSTANCE.convertList(page.getRecords()));
        return Results.ok(pageResult);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Results<Void> deleteGroup(Integer id) {
        if (id == null || id < 0) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }
        Groups groups = getById(id);
        if (groups == null) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }
        groups.setExist(false);
        updateById(groups);
        return Results.ok("删除成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Results<Void> updateGroup(GroupsVO groupsVO) {
        if (groupsVO == null) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }
        if (groupsVO.getId() == null || groupsVO.getId() < 0) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }
        if (StringUtil.isEmpty(groupsVO.getName()) || StringUtil.isEmpty(groupsVO.getDescription())) {
            throw new DeslreException(ResultCodeEnum.EMPTY_VALUE);
        }

        Groups groups = getById(groupsVO.getId());
        if (groups == null) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }

        groups = GroupsConvert.INSTANCE.convert(groupsVO);
        updateById(groups);

        return Results.ok("更新成功");
    }

    @Override
    public Results<List<GroupsVO>> getOptions(Integer count) {
        QueryWrapper<Groups> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id").last("LIMIT 100");
        List<Groups> groupsList = list(queryWrapper);
        List<GroupsVO> converted = GroupsConvert.INSTANCE.convertList(groupsList);
        return Results.ok(converted);
    }


    private QueryWrapper<Groups> getWrapper(GroupsQuery query) {
        QueryWrapper<Groups> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtil.isNotEmpty(String.valueOf(query.getId())), "id", query.getId());
        wrapper.eq(StringUtil.isNotEmpty(query.getName()), "name", query.getName());
        wrapper.eq(StringUtil.isNotEmpty(query.getDescription()), "description", query.getDescription());
        wrapper.eq("exist", true);

        return wrapper;
    }

}
