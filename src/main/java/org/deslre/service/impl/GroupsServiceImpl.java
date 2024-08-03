package org.deslre.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.deslre.entity.po.Groups;
import org.deslre.mapper.GroupsMapper;
import org.deslre.result.Results;
import org.deslre.service.GroupsService;
import org.springframework.stereotype.Service;

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
    public Results<List<Groups>> getAllGroups() {
        QueryWrapper<Groups> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("exist", true);
        List<Groups> groups = list(queryWrapper);
        return Results.ok(groups);
    }
}
