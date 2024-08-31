package org.deslre.controller;

import org.deslre.annotation.GlobalInterceptor;
import org.deslre.annotation.VerifyParam;
import org.deslre.entity.dto.SessionWebUserDto;
import org.deslre.result.Constants;
import org.deslre.result.Results;
import org.deslre.service.UserInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * ClassName: UserInfoController
 * Description: 用户CRUD
 * Author: Deslrey
 * Date: 2024-08-24 22:44
 * Version: 1.0
 */

@RestController
@RequestMapping("userInfo")
public class UserInfoController extends BaseController {

    @Resource
    private UserInfoService userInfoService;

    @PostMapping("login")
    @GlobalInterceptor(checkLogin = false, checkParams = true)
    public Results<SessionWebUserDto> login(HttpSession session,
                                            @VerifyParam(required = true) String email,
                                            @VerifyParam(required = true) String password) {

        try {
            SessionWebUserDto sessionWebUserDto = userInfoService.login(email, password);
            session.setAttribute(Constants.SESSION_KEY, sessionWebUserDto);
            return Results.ok(sessionWebUserDto);
        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY);
        }
    }

}
