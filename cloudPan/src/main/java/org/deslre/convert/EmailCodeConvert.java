package org.deslre.convert;

import org.deslre.entity.po.EmailCode;
import org.deslre.entity.vo.EmailCodeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * ClassName: EmailCodeConvert
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-07-28 20:10
 * Version: 1.0
 */
@Mapper(componentModel = "spring")
public interface EmailCodeConvert {
    EmailCodeConvert INSTANCE = Mappers.getMapper(EmailCodeConvert.class);

    EmailCode convert(EmailCodeVO vo);

    EmailCodeVO convert(EmailCode entity);

    List<EmailCodeVO> convertList(List<EmailCode> list);

}