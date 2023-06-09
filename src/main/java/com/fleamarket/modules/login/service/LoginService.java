package com.fleamarket.modules.login.service;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.fleamarket.code.exception.CustomException;
import com.fleamarket.common.cache.FleaMarketCache;
import com.fleamarket.common.constants.Constants;
import com.fleamarket.modules.login.dto.RegisteredOrLoginDto;
import com.fleamarket.modules.user.domain.User;
import com.fleamarket.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;


@Service
public class LoginService {

    @Autowired
    private UserService userService;

    /**
     * 生成令牌并缓存
     *
     * @param user 用户
     * @return {@link String}
     */
    public String generateTokenAndCache(User user) {
        String userStr = JSON.toJSONString(user);
        String token = DigestUtil.md5Hex(userStr);
        FleaMarketCache.setCache(String.format(Constants.TOKEN, token), userStr, DateUnit.DAY.getMillis() * 7);
        return token;
    }

    /**
     * 注册或登录
     *
     * @param registeredOrLoginDto 注册或登录dto
     * @return {@link User}
     */
    public User registeredOrLogin(RegisteredOrLoginDto registeredOrLoginDto) {
        if (!detectionCode(registeredOrLoginDto.getEmail(), registeredOrLoginDto.getCode())) {
            throw new CustomException("验证码错误/失效");
        }
        User user = userService.lambdaQuery().eq(User::getUserName, registeredOrLoginDto.getEmail()).one();
        if (Objects.isNull(user)) {
            User newUser = new User();
            newUser.setUserName(registeredOrLoginDto.getEmail());
            newUser.setNickName("flea".concat(RandomUtil.randomString(5)));
            userService.save(newUser);
            return newUser;
        }
        if (!StrUtil.equals(user.getState(), BigDecimal.ONE.toString())) {
            throw new CustomException("账号异常!");
        }
        return user;
    }

    /**
     * 检测验证码
     *
     * @param email 电子邮件
     * @param code  验证码
     * @return {@link Boolean}
     */
    private Boolean detectionCode(String email, String code) {
        return StrUtil.equals(FleaMarketCache.getCache(String.format(Constants.EMAIL, email)), code);
    }
}
