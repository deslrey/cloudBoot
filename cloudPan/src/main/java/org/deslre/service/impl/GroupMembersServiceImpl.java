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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.deslre.result.Constants.NODE_TYPE_ENTITY;
import static org.deslre.result.Constants.NODE_TYPE_PERSON;

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
    @Transactional(rollbackFor = Exception.class)
    public Results<Void> updateNodeData(SingleNodeVO singleNode) {

        if (StringUtil.isNull(singleNode) || StringUtil.isNull(singleNode.getId())
                || StringUtil.isNull(singleNode.getGroupId()) || StringUtil.isEmpty(singleNode.getNodeType())) {
            throw new DeslreException(ResultCodeEnum.EMPTY_VALUE);
        }
        String role = singleNode.getRole();

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

        if (StringUtil.isNotEmpty(role)) {
            groupMembers.setRole(role);
            updateById(groupMembers);
        }

        if (singleNode.getNodeType().equals(NODE_TYPE_PERSON)) {
            updatePerson(singleNode);
        } else if (singleNode.getNodeType().equals(NODE_TYPE_ENTITY)) {
            updateEntities(singleNode);
        } else {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }

        return Results.ok("更新完成");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Results<Map<String, List<SingleNodeVO>>> getAllData(Integer id) {
        if (StringUtil.isNull(id) || id <= 0) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }

        QueryWrapper<GroupMembers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_id", id);
        queryWrapper.eq("exist", true);
        List<GroupMembers> membersList = list(queryWrapper);
        if (membersList == null || membersList.isEmpty()) {
            throw new DeslreException("该组数据暂无关系数据");
        }
        Map<String, List<SingleNodeVO>> map = new HashMap<>(15);
        List<Integer> personListId = new ArrayList<>(10);
        List<Integer> entityListId = new ArrayList<>(10);
        for (GroupMembers groupMembers : membersList) {
            String nodeType = groupMembers.getNodeType();
            if (StringUtil.isEmpty(nodeType))
                continue;
            if (NODE_TYPE_PERSON.equals(nodeType)) {
                personListId.add(groupMembers.getNodeId());
            } else if (NODE_TYPE_ENTITY.equals(nodeType)) {
                entityListId.add(groupMembers.getNodeId());
            }
        }
        List<SingleNodeVO> nodeVOList = null;
        SingleNodeVO singleNodeVO;
        List<Persons> personsList = personsService.listByIds(personListId);
        if (personsList != null && !personsList.isEmpty()) {
            nodeVOList = new ArrayList<>(15);
            for (Persons persons : personsList) {
                if (persons.getExist()) {
                    singleNodeVO = new SingleNodeVO();
                    singleNodeVO.setId(persons.getId());
                    singleNodeVO.setNodeType(NODE_TYPE_PERSON);
                    singleNodeVO.setName(persons.getName());
                    singleNodeVO.setAge(persons.getAge());
                    singleNodeVO.setGender(persons.getGender());
                    singleNodeVO.setBirthplace(persons.getBirthplace());
                    singleNodeVO.setIdCard(persons.getIdCard());
                    singleNodeVO.setDescription(persons.getDescription());
                    singleNodeVO.setExist(true);
                    nodeVOList.add(singleNodeVO);
                }
            }
            map.put(NODE_TYPE_PERSON, nodeVOList);
        }


        List<Entities> entitiesList = entitiesService.listByIds(entityListId);
        if (entitiesList != null && !entitiesList.isEmpty()) {
            nodeVOList = new ArrayList<>(15);
            for (Entities entities : entitiesList) {
                if (entities.getExist()) {
                    singleNodeVO = new SingleNodeVO();
                    singleNodeVO.setId(entities.getId());
                    singleNodeVO.setNodeType(NODE_TYPE_ENTITY);
                    singleNodeVO.setName(entities.getName());
                    singleNodeVO.setDescription(entities.getDescription());
                    singleNodeVO.setExist(true);
                    nodeVOList.add(singleNodeVO);
                }
            }
            map.put(NODE_TYPE_ENTITY, nodeVOList);
        }


        return Results.ok(map);
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
    }
}
