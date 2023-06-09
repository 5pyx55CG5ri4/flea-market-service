package com.fleamarket.code.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础返回
 */
@Data
@AllArgsConstructor
public class BaseResponse implements Serializable {

    /**
     * 结果码
     */
    private String code;

    /**
     * 消息内容
     */
    private String message;


}
