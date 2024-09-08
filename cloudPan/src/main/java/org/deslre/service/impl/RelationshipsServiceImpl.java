package org.deslre.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.deslre.convert.EntitiesConvert;
import org.deslre.convert.PersonsConvert;
import org.deslre.entity.po.Entities;
import org.deslre.entity.po.Groups;
import org.deslre.entity.po.Persons;
import org.deslre.entity.po.Relationships;
import org.deslre.entity.vo.EntitiesVO;
import org.deslre.entity.vo.PersonsVO;
import org.deslre.entity.vo.RelationshipsVO;
import org.deslre.entity.vo.SingleNodeVO;
import org.deslre.exception.DeslreException;
import org.deslre.mapper.RelationshipsMapper;
import org.deslre.result.ResultCodeEnum;
import org.deslre.result.Results;
import org.deslre.service.EntitiesService;
import org.deslre.service.GroupsService;
import org.deslre.service.PersonsService;
import org.deslre.service.RelationshipsService;
import org.deslre.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static org.deslre.result.Constants.NODE_TYPE_ENTITY;
import static org.deslre.result.Constants.NODE_TYPE_PERSON;

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

    @Resource
    private PersonsService personsService;

    @Resource
    private EntitiesService entitiesService;

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

    @Override
    public Results<Void> updateNodeData(SingleNodeVO singleNodeVO) {
        if (StringUtil.isNull(singleNodeVO)) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }

        if ((StringUtil.isNull(singleNodeVO.getId()) || singleNodeVO.getId() <= 0) || (StringUtil.isNull(singleNodeVO.getGroupId()) || singleNodeVO.getGroupId() <= 0)) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }

        String nodeType = singleNodeVO.getNodeType();
        if (StringUtil.isEmpty(nodeType)) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }

        switch (nodeType) {
            case NODE_TYPE_PERSON:
                updatePersonData(singleNodeVO);
                break;
            case NODE_TYPE_ENTITY:
                updateEntityData(singleNodeVO);
                break;
            default:
                throw new DeslreException(ResultCodeEnum.CODE_600);
        }

        return Results.ok();
    }

    private void updateEntityData(SingleNodeVO singleNodeVO) {
        System.out.println("updatePersonData = " + singleNodeVO);
        Entities entities = EntitiesConvert.INSTANCE.convert(singleNodeVO);
        EntitiesVO convert = EntitiesConvert.INSTANCE.convert(entities);
        entitiesService.updateEntities(convert, singleNodeVO.getGroupId());
        System.out.println("entity更新完成");
    }

    private void updatePersonData(SingleNodeVO singleNodeVO) {
        System.out.println("updateEntityData = " + singleNodeVO);
        Persons persons = PersonsConvert.INSTANCE.convert(singleNodeVO);
        PersonsVO convert = PersonsConvert.INSTANCE.convert(persons);
        personsService.updatePerson(convert, singleNodeVO.getGroupId());
        System.out.println("person更新完成");
    }
}



























