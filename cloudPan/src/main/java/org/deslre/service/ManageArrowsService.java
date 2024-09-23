package org.deslre.service;

import org.deslre.entity.dto.SessionWebUserDto;
import org.deslre.entity.po.ManageArrows;
import org.deslre.entity.vo.ManageArrowsVO;
import org.deslre.page.PageResult;
import org.deslre.query.ManageArrowsQuery;
import org.deslre.result.Results;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author author
 * @since 2024-09-19
 */
public interface ManageArrowsService extends BaseService<ManageArrows> {

    Results<PageResult<ManageArrowsVO>> getPageData(ManageArrowsQuery query);

    Results<Void> updateArrowsData(ManageArrowsVO arrowsVO);

    Results<Void> addArrowsData(String arrowName, String nickName);

    Results<Void> deleteArrowsData(ManageArrowsVO arrowsVO);

    Results<List<String>> getArrowsList( SessionWebUserDto userDto);
}
