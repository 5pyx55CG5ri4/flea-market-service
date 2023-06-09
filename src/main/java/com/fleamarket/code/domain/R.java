package com.fleamarket.code.domain;


import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Objects;

/**
 * 操作消息提醒
 */
public class R extends HashMap<String, Object> {
    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    private static final long serialVersionUID = 1L;

    private final String requestId = MDC.get("requestId");

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public R() {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public R(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        super.put("reqId", requestId);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public R(int code, Object msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        super.put("reqId", requestId);
        if (Objects.nonNull(data)) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static R success() {
        return R.success("operation success");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static R success(Object data) {
        return R.success("operation success", data);
    }


    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static R success(String msg, Object data) {
        return new R(200, msg, data);
    }

    /**
     * 返回错误消息
     *
     */
    public static R error() {
        return R.error("operation error");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static R error(Object msg) {
        return R.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static R error(Object msg, Object data) {
        return new R(500, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static R error(int code, String msg) {
        return new R(code, msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param data 返回内容
     * @return 警告消息
     */
    public static R error(int code, Object data) {
        return new R(code, "业务错误", data);
    }
}
