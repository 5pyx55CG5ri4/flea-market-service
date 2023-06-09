package com.fleamarket.common.auth;

import com.alibaba.fastjson.JSONObject;
import com.fleamarket.code.exception.CustomException;
import com.fleamarket.common.annotation.UnAuth;
import com.fleamarket.common.cache.FleaMarketCache;
import com.fleamarket.common.constants.Constants;
import com.fleamarket.modules.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 访问拦截器
 */
@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("request url [{}]", request.getRequestURI());
        // 如果不需要拦截 则直接返回
        boolean needFilter = isNeedFilter(request, handler);
        if (needFilter) {
            return true;
        }
        // 认证信息在header 中的key
        String token = request.getHeader("token");
        // 不需拦截的请求若包含认证信息  则继续  获取用户信息
        if (StringUtils.isBlank(token)) {
            log.info("AuthorityInterceptor preHandle fail....");
            throw new CustomException(401, "请先登录!");
        }
        // 获得凭证
        User user = getUserForCache(token);
        // 没有凭证 直接返回
        if (Objects.isNull(user)) {
            log.info("AuthorityInterceptor preHandle fail....");
            throw new CustomException(401, "请先登录!");
        }
        UserHolder.setMember(user, token);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserHolder.remove();
    }


    private User getUserForCache(String token) {
        String json = FleaMarketCache.getCache(String.format(Constants.TOKEN, token));
        return StringUtils.isEmpty(json) ? null : JSONObject.parseObject(json, User.class);
    }

    /**
     * 判断是否需要拦截
     * 返回 false 表示需要拦截  true表示直接放行
     */
    private boolean isNeedFilter(HttpServletRequest request, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return false;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        UnAuth annotation = handlerMethod.getMethod().getAnnotation(UnAuth.class);
        return Objects.nonNull(annotation);
    }

}
