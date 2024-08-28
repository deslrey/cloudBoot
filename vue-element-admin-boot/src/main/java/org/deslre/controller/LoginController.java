package org.deslre.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: LoginController
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-24 22:44
 * Version: 1.0
 */

@RestController
@RequestMapping("user")
public class LoginController {


    @PostMapping("login")
    public void Login() {
        System.out.println("this = " + this);
    }


}
