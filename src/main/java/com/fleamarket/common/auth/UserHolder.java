package com.fleamarket.common.auth;

import cn.hutool.core.util.ObjectUtil;
import com.fleamarket.modules.user.domain.User;
import lombok.experimental.UtilityClass;

/**
 * 记录当前登录用户的信息
 * <p>
 * 1. 不要在多线程场景直接使用，需要先将数据取出后传到线程中
 * 2. 不要在业务层中使用，应在控制器中取出传递给业务层
 */
@UtilityClass
public class UserHolder {

    private final ThreadLocal<User> memberThreadLocal = new ThreadLocal<>();

    private final ThreadLocal<String> tokenThreadLocal = new ThreadLocal<>();

    /**
     * 获取会员ID，如果用户未登录，返回0
     *
     * @return 会员ID
     */
    public Long getUserId() {
        User member = getMember();
        if (member == null) {
            return 0L;
        }
        return ObjectUtil.defaultIfNull(member.getId(), 0L);
    }


    /**
     * 获取用户信息，如果未登录返回空
     *
     * @return 用户信息
     */
    public User getMember() {
        return memberThreadLocal.get();
    }

    /**
     * 获取用户的Token
     *
     * @return 用户Token
     */
    public static String getToken() {
        return tokenThreadLocal.get();
    }

    public static void setMember(User member, String token) {
        memberThreadLocal.set(member);
        tokenThreadLocal.set(token);
    }

    public void remove() {
        memberThreadLocal.remove();
        tokenThreadLocal.remove();
    }
}
