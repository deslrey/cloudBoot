package org.deslre.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.deslre.convert.EntitiesConvert;
import org.deslre.convert.PersonsConvert;
import org.deslre.entity.po.Entities;
import org.deslre.entity.po.GroupMembers;
import org.deslre.entity.po.Persons;
import org.deslre.entity.vo.EntitiesVO;
import org.deslre.exception.DeslreException;
import org.deslre.mapper.EntitiesMapper;
import org.deslre.result.ResultCodeEnum;
import org.deslre.result.Results;
import org.deslre.service.EntitiesService;
import org.deslre.service.GroupMembersService;
import org.deslre.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * ClassName: EntitiesServiceImpl
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:37
 * Version: 1.0
 */
@Service
public class EntitiesServiceImpl extends BaseServiceImpl<EntitiesMapper, Entities> implements EntitiesService {

    @Resource
    private GroupMembersService groupMembersService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Results<Void> updateEntities(EntitiesVO entitiesVO, Integer groupId) {

        if (StringUtil.isNull(entitiesVO) || StringUtil.isNull(groupId)) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }

        if (StringUtil.isNull(entitiesVO.getId()) || (entitiesVO.getId() <= 0 || groupId <= 0)) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }

        QueryWrapper<GroupMembers> groupsQueryWrapper = new QueryWrapper<>();
        groupsQueryWrapper.eq("group_id", groupId);
        groupsQueryWrapper.eq("node_id", entitiesVO.getId());
        groupsQueryWrapper.eq("node_type", "entity");
        groupsQueryWrapper.eq("exist", true);
        GroupMembers groupMembers = groupMembersService.getOne(groupsQueryWrapper);
        if (groupMembers == null) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }

        QueryWrapper<Entities> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", entitiesVO.getId());
        queryWrapper.eq("exist", true);

        Entities entities = getOne(queryWrapper);
        if (StringUtil.isNull(entities)) {
            throw new DeslreException(ResultCodeEnum.FAIL);
        }

        entities = EntitiesConvert.INSTANCE.convert(entitiesVO);
        updateById(entities);

        return Results.ok("节点信息更新完成");
    }
}
