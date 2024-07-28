package org.deslre.controller;

import org.deslre.annotation.GlobalInterceptor;
import org.deslre.annotation.VerifyParam;
import org.deslre.entity.dto.SessionWebUserDto;
import org.deslre.entity.enums.FileDelFlagEnums;
import org.deslre.entity.vo.FileInfoVO;
import org.deslre.page.PageResult;
import org.deslre.query.FileInfoQuery;
import org.deslre.result.Results;
import org.deslre.service.FileInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
/**
 * ClassName: RecycleController
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-07-28 21:11
 * Version: 1.0
 */
@RestController
@RequestMapping("/recycle")
public class RecycleController extends BaseController{

    @Resource
    private FileInfoService fileInfoService;

    @RequestMapping("/loadRecycleList")
    @GlobalInterceptor(checkParams = true)
    public Results<PageResult<FileInfoVO>> loadRecycleList(HttpSession session, Integer pageNo, Integer pageSize) {
        FileInfoQuery query = new FileInfoQuery();
        query.setPageSize(pageSize);
        query.setPageNo(pageNo);
        query.setUserId(getUserInfoFromSession(session).getUserId());
        query.setOrderBy("recovery_time desc");
        query.setDelFlag(FileDelFlagEnums.RECYCLE.getFlag());
        PageResult<FileInfoVO> page = fileInfoService.page(query);
        return Results.ok(page);
    }

    // 回收站文件还原到根目录
    @RequestMapping("/recoverFile")
    @GlobalInterceptor(checkParams = true)
    public Results<String> recoverFile(HttpSession session,
                                      @VerifyParam(required = true) String fileIds) {
        SessionWebUserDto webUserDto = getUserInfoFromSession(session);
        fileInfoService.recoverFileBatch(webUserDto.getUserId(), fileIds);
        return Results.ok();
    }

    @RequestMapping("/delFile")
    @GlobalInterceptor(checkParams = true)
    public Results<String> delFile(HttpSession session,
                                  @VerifyParam(required = true) String fileIds) {
        SessionWebUserDto webUserDto = getUserInfoFromSession(session);
        fileInfoService.delFileBatch(webUserDto.getUserId(), fileIds, false);
        return Results.ok();
    }
}
