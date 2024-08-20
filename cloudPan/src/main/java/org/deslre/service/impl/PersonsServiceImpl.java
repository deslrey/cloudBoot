package org.deslre.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.deslre.convert.PersonsConvert;
import org.deslre.entity.po.GroupMembers;
import org.deslre.entity.po.Groups;
import org.deslre.entity.po.Persons;
import org.deslre.entity.vo.PersonsVO;
import org.deslre.exception.DeslreException;
import org.deslre.mapper.PersonsMapper;
import org.deslre.result.ResultCodeEnum;
import org.deslre.result.Results;
import org.deslre.service.GroupMembersService;
import org.deslre.service.GroupsService;
import org.deslre.service.PersonsService;
import org.deslre.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ClassName: PersonsServiceImpl
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:40
 * Version: 1.0
 */
@Service
public class PersonsServiceImpl extends BaseServiceImpl<PersonsMapper, Persons> implements PersonsService {

    @Resource
    private GroupMembersService groupMembersService;

    @Override
    public Results<Void> updatePerson(PersonsVO personsVO, Integer groupId) {

        if (StringUtil.isNull(personsVO) || StringUtil.isNull(groupId)) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }

        if (StringUtil.isNull(personsVO.getId()) || (personsVO.getId() <= 0 || groupId <= 0)) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }

        QueryWrapper<GroupMembers> groupsQueryWrapper = new QueryWrapper<>();
        groupsQueryWrapper.eq("group_id", groupId);
        groupsQueryWrapper.eq("node_id", personsVO.getId());
        groupsQueryWrapper.eq("node_type", "person");
        groupsQueryWrapper.eq("exist", true);
        GroupMembers groupMembers = groupMembersService.getOne(groupsQueryWrapper);
        if (groupMembers == null) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }

        QueryWrapper<Persons> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", personsVO.getId());
        queryWrapper.eq("exist", true);

        Persons persons = getOne(queryWrapper);
        if (StringUtil.isNull(persons)) {
            throw new DeslreException(ResultCodeEnum.FAIL);
        }

        persons = PersonsConvert.INSTANCE.convert(personsVO);
        updateById(persons);

        return Results.ok("节点信息更新完成");
    }


}
