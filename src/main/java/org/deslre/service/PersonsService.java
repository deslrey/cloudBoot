package org.deslre.service;

import org.deslre.entity.po.Persons;
import org.deslre.entity.vo.PersonsVO;
import org.deslre.result.Results;

/**
 * ClassName: PersonsService
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:35
 * Version: 1.0
 */
public interface PersonsService extends BaseService<Persons>{
    Results<Void> updatePerson(PersonsVO personsVO, Integer groupId);
}
