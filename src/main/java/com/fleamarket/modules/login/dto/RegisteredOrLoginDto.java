package com.fleamarket.modules.login.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 注册或登录dto
 *
 * @author zhuliyou
 * @date 2023/04/28
 */
@Data
public class RegisteredOrLoginDto {

    @NotEmpty(message = "邮箱不能为空")
    private String email;

    @NotEmpty(message = "验证码不能为空")
    private String code;

}
