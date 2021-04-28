package cn.fleamarket.common;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 返回结果类
 *
 * @author zhuliyou
 * @date 2021/04/28
 */
@Data
@AllArgsConstructor
public class R<T> extends PageResult implements Serializable {

    private static final Integer OK_CODE = 200;

    private static final String OK_MSG = "请求成功!";

    private static final Integer ERROR_CODE = 500;

    private static final String ERROR_MSG = "服务器异常!";


    /**
     * 代码
     */
    private Integer code;

    /**
     * 味精
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    private R() {

    }

    private static <T> R<T> build(Integer code, String msg, T data) {
        return new R<>(code, msg, data);
    }


    public static <T> R<T> ok(T data) {
        return build(OK_CODE, OK_MSG, data);
    }


    public static <T> R<T> ok(String msg, T data) {
        return build(OK_CODE, msg, data);
    }


    public static <T> R<T> ok(Integer code, String msg, T data) {
        return build(code, msg, data);
    }


    public static <T> R<T> error(T data) {
        return build(ERROR_CODE, ERROR_MSG, data);
    }

    public static <T> R<T> error(String msg) {
        return build(ERROR_CODE, msg, null);
    }

    public static <T> R<T> error(String msg, T data) {
        return build(ERROR_CODE, msg, data);
    }


    public static <T> R<T> error(Integer code, String msg, T data) {
        return build(code, msg, data);
    }


    public static <T> R<List<T>> pageBuild(Long total, Boolean next, Boolean previous, List<T> data) {
        R<List<T>> build = build(OK_CODE, OK_MSG, data);
        build.setTotal(total);
        build.setNext(next);
        build.setPrevious(previous);
        return build;
    }

}
