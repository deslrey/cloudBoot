package org.deslre.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.deslre.convert.EntitiesConvert;
import org.deslre.convert.PersonsConvert;
import org.deslre.entity.po.Entities;
import org.deslre.entity.po.GroupMembers;
import org.deslre.entity.po.Persons;
import org.deslre.entity.vo.SingleNodeVO;
import org.deslre.exception.DeslreException;
import org.deslre.mapper.GroupMembersMapper;
import org.deslre.result.ResultCodeEnum;
import org.deslre.result.Results;
import org.deslre.service.EntitiesService;
import org.deslre.service.GroupMembersService;
import org.deslre.service.PersonsService;
import org.deslre.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ClassName: GroupMembersServiceImpl
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:38
 * Version: 1.0
 */
@Service
public class GroupMembersServiceImpl extends BaseServiceImpl<GroupMembersMapper, GroupMembers> implements GroupMembersService {

    @Resource
    private PersonsService personsService;
    @Resource
    private EntitiesService entitiesService;

    @Override
    public Results<Void> updateNodeData(SingleNodeVO singleNode) {
        if (StringUtil.isNull(singleNode) || StringUtil.isNull(singleNode.getId()) || StringUtil.isNull(singleNode.getGroupId()) || StringUtil.isEmpty(singleNode.getNodeType())) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }

        if (singleNode.getId() <= 0 || singleNode.getGroupId() <= 0) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }

        QueryWrapper<GroupMembers> groupMembersQueryWrapper = new QueryWrapper<>();
        groupMembersQueryWrapper.eq("group_id", singleNode.getGroupId());
        groupMembersQueryWrapper.eq("node_id", singleNode.getId());
        groupMembersQueryWrapper.eq("node_type", singleNode.getNodeType());
        groupMembersQueryWrapper.eq("exist", true);

        GroupMembers groupMembers = getOne(groupMembersQueryWrapper);
        if (groupMembers == null) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }

        if (singleNode.getNodeType().equals("person")) {
            updatePerson(singleNode);
        } else if (singleNode.getNodeType().equals("entity")) {
            updateEntities(singleNode);
        } else {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }


        return Results.ok("更新完成");
    }


    private void updatePerson(SingleNodeVO singleNode) {
        Persons convert = PersonsConvert.INSTANCE.convert(singleNode);

        QueryWrapper<Persons> wrapper = new QueryWrapper<>();
        wrapper.eq("id", convert.getId());
        wrapper.eq("exist", convert.getExist());
        Persons persons = personsService.getOne(wrapper);
        if (persons == null) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }
        personsService.updateById(convert);

        System.out.println("persons = " + convert);
    }

    private void updateEntities(SingleNodeVO singleNode) {
        Entities convert = EntitiesConvert.INSTANCE.convert(singleNode);
        QueryWrapper<Entities> wrapper = new QueryWrapper<>();
        wrapper.eq("id", convert.getId());
        wrapper.eq("exist", convert.getExist());
        Entities entities = entitiesService.getOne(wrapper);
        if (entities == null) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }

        entitiesService.updateById(convert);

        System.out.println("entities = " + convert);
    }
}
