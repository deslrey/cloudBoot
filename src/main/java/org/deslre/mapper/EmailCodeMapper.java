package org.deslre.mapper;

import org.apache.ibatis.annotations.Param;
import org.deslre.entity.po.EmailCode;

/**
 * ClassName: EmailCodeMapper
 * Description: 邮箱验证码
 * Author: Deslrey
 * Date: 2024-07-28 20:51
 * Version: 1.0
 */
public interface EmailCodeMapper extends BaseDao<EmailCode> {

    void disableEmailCode(@Param("email") String email);
}