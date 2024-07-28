package org.deslre.service;

import java.util.List;

import org.deslre.entity.dto.SessionWebUserDto;
import org.deslre.entity.po.UserInfo;
import org.deslre.entity.vo.UserInfoVO;
import org.deslre.page.PageResult;
import org.deslre.query.UserInfoQuery;

/**
 * ClassName: UserInfoService
 * Description: 用户CRUD
 * Author: Deslrey
 * Date: 2024-07-28 20:35
 * Version: 1.0
 */
public interface UserInfoService extends BaseService<UserInfo> {

    PageResult<UserInfoVO> page(UserInfoQuery query);

    void save(UserInfoVO vo);

    void update(UserInfoVO vo);

    void delete(List<Long> idList);

    void register(String email, String nickName, String password, String emailCode);

    SessionWebUserDto login(String email, String password);

    void resetPwd(String email, String password, String emailCode);
}