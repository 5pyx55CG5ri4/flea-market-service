package com.fleamarket.modules.login.controller;


import com.fleamarket.code.domain.R;
import com.fleamarket.common.annotation.UnAuth;
import com.fleamarket.modules.email.EmailService;
import com.fleamarket.modules.login.dto.RegisteredOrLoginDto;
import com.fleamarket.modules.login.service.LoginService;
import com.fleamarket.modules.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 登录控制器
 *
 * @author zhuliyou
 * @date 2023/04/28
 */
@RestController
public class LoginController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private LoginService loginService;

    /**
     * 发送验证码
     *
     * @param email 电子邮件
     * @return {@link R}<{@link Boolean}>
     */
    @GetMapping("sendVerificationCode/{email}")
    @UnAuth
    public R sendVerificationCode(@PathVariable("email") String email) {
        emailService.asyncSendEmailCode(email);
        return R.success();
    }

    /**
     * 注册或登录
     *
     * @param registeredOrLoginDto 注册或登录dto
     * @return {@link R}<{@link String}>
     */
    @PostMapping("registeredOrLogin")
    @UnAuth
    public R registeredOrLogin(@RequestBody @Validated RegisteredOrLoginDto registeredOrLoginDto) {
        User user = loginService.registeredOrLogin(registeredOrLoginDto);
        String token = loginService.generateTokenAndCache(user);
        user.setToken(token);
        return R.success(user);
    }

}
