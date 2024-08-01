package org.deslre.controller;

import org.deslre.annotation.GlobalInterceptor;
import org.deslre.annotation.VerifyParam;
import org.deslre.convert.EmailCodeConvert;
import org.deslre.entity.po.EmailCode;
import org.deslre.entity.vo.EmailCodeVO;
import org.deslre.exception.DeslreException;
import org.deslre.page.PageResult;
import org.deslre.query.EmailCodeQuery;
import org.deslre.result.Constants;
import org.deslre.result.Results;
import org.deslre.service.EmailCodeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * ClassName: EmailCodeController
 * Description: 邮箱验证码
 * Author: Deslrey
 * Date: 2024-07-28 20:47
 * Version: 1.0
 */
@RestController
@RequestMapping("emailCode")
public class EmailCodeController {
    @Resource
    private  EmailCodeService emailCodeService;

    @RequestMapping("/sendEmailCode")
    @GlobalInterceptor(checkParams = true, checkLogin = false)
    public Results<String> sendEmailCode(HttpSession session,
                                         @VerifyParam(required = true) String email,
                                         @VerifyParam(required = true) String checkCode,
                                         @VerifyParam(required = true) Integer type) {
        try {
            if (!checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY_EMAIL))) {
                throw new DeslreException("图片验证码不正确");
            }
            emailCodeService.sendEmailCode(email, type);
            return Results.ok("邮件发送成功");
        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY_EMAIL);
        }
    }

    @GetMapping("page")

    public Results<PageResult<EmailCodeVO>> page(@Valid EmailCodeQuery query){
        PageResult<EmailCodeVO> page = emailCodeService.page(query);

        return Results.ok(page);
    }

    @GetMapping("{id}")

    public Results<EmailCodeVO> get(@PathVariable("id") Long id){
        EmailCode entity = emailCodeService.getById(id);

        return Results.ok(EmailCodeConvert.INSTANCE.convert(entity));
    }

    @PostMapping

    public Results<String> save(@RequestBody EmailCodeVO vo){
        emailCodeService.save(vo);

        return Results.ok();
    }

    @PutMapping

    public Results<String> update(@RequestBody @Valid EmailCodeVO vo){
        emailCodeService.update(vo);

        return Results.ok();
    }

    @DeleteMapping

    public Results<String> delete(@RequestBody List<Long> idList){
        emailCodeService.delete(idList);

        return Results.ok();
    }
}