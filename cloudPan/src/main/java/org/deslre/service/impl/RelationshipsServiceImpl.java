package org.deslre.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.deslre.entity.po.Groups;
import org.deslre.entity.po.Relationships;
import org.deslre.entity.vo.RelationshipsVO;
import org.deslre.exception.DeslreException;
import org.deslre.mapper.RelationshipsMapper;
import org.deslre.result.ResultCodeEnum;
import org.deslre.result.Results;
import org.deslre.service.GroupsService;
import org.deslre.service.RelationshipsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.util.List;

/**
 * ClassName: RelationshipsServiceImpl
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:40
 * Version: 1.0
 */
@Service
public class RelationshipsServiceImpl extends BaseServiceImpl<RelationshipsMapper, Relationships> implements RelationshipsService {

    @Resource
    private GroupsService groupsService;

    @Override
    public Results<List<RelationshipsVO>> getGroupRela(Integer id) {
        if (id <= 0) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }

        QueryWrapper<Groups> groupsQueryWrapper = new QueryWrapper<>();
        groupsQueryWrapper.eq("id", id);
        groupsQueryWrapper.eq("exist", true);
        Groups groups = groupsService.getOne(groupsQueryWrapper);
        if (groups == null) {
            throw new DeslreException("当前组数据不存在");
        }

        List<RelationshipsVO> list = baseMapper.findByGroupId(id);
        return Results.ok(list);

    }
}
