package org.deslre.service;

import org.deslre.entity.po.EmailCode;
import org.deslre.entity.vo.EmailCodeVO;
import org.deslre.page.PageResult;
import org.deslre.query.EmailCodeQuery;

import java.util.List;

/**
 * ClassName: EmailCodeService
 * Description: 邮箱验证码
 * Author: Deslrey
 * Date: 2024-07-28 20:41
 * Version: 1.0
 */
public interface EmailCodeService extends BaseService<EmailCode> {

    PageResult<EmailCodeVO> page(EmailCodeQuery query);

    void save(EmailCodeVO vo);

    void update(EmailCodeVO vo);

    void delete(List<Long> idList);

    void sendEmailCode(String email, Integer type);

    void checkCode(String email, String emailCode);
}