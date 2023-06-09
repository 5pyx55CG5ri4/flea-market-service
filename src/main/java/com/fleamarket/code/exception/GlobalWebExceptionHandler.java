package com.fleamarket.code.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 全局异常
 */
@RestControllerAdvice
public class GlobalWebExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalWebExceptionHandler.class);


    @ExceptionHandler(value = CustomException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseResponse exceptionHandler(HttpServletRequest request, CustomException ex) {
        logger.error("do [{}] on [{}] failed. exMsg:{}", request.getMethod(), request.getRequestURL(),
                ex.getLocalizedMessage());
        if (logger.isDebugEnabled()) {
            logger.error("queryString:{}, parameterMap: {}", request.getQueryString(), request.getParameterMap(), ex);
        }
        return new BaseResponse(Optional.ofNullable(ex.getCode()).map(String::valueOf).orElse("500"), ex.getLocalizedMessage());
    }

    /**
     * 全局异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseResponse exceptionHandler(HttpServletRequest request, Exception ex) {
        logger.error("do [{}] on [{}] failed. exMsg:{}", request.getMethod(), request.getRequestURL(),
                ex.getLocalizedMessage());
        if (logger.isDebugEnabled()) {
            logger.error("queryString:{}, parameterMap: {}", request.getQueryString(), request.getParameterMap(), ex);
        }
        return new BaseResponse("500", ex.getLocalizedMessage());
    }
}
