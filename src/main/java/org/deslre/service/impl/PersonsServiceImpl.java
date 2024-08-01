package org.deslre.service.impl;

import org.deslre.entity.po.Persons;
import org.deslre.mapper.PersonsMapper;
import org.deslre.service.PersonsService;
import org.springframework.stereotype.Service;

/**
 * ClassName: PersonsServiceImpl
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:40
 * Version: 1.0
 */
@Service
public class PersonsServiceImpl extends BaseServiceImpl<PersonsMapper, Persons> implements PersonsService {
}
