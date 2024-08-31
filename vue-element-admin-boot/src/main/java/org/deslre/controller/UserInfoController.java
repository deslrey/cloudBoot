package org.deslre.controller;

import org.deslre.annotation.GlobalInterceptor;
import org.deslre.result.Results;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping("login")
    public void Login() {
        System.out.println("this = " + this);
    }

    @GlobalInterceptor(checkLogin = false)
    @PostMapping("check")
    public Results<String> checkLogin() {
        System.out.println("this = " + this);
        return Results.ok("检查成功");
    }

}
