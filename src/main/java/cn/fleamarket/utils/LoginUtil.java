package cn.fleamarket.utils;


import cn.fleamarket.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 登录工具类
 *
 * @author zhuliyou
 * @date 2021/04/30
 */
@Component
public class LoginUtil {

    @Autowired
    private RedisUtil<String, User> redisUtil;

    private final static String TOKEN_PREFIX = "F_Token:";


    public String setToken(User user) {
        String uuid = StringTool.getUUID();
        redisUtil.set(TOKEN_PREFIX + uuid, user, 30L);
        return uuid;
    }

    public Boolean isSystemToken(String token) {
        User user = redisUtil.get(TOKEN_PREFIX + token);
        return Objects.nonNull(user);
    }

    public User getUserByToken(String token) {
        return redisUtil.get(TOKEN_PREFIX + token);
    }


}
