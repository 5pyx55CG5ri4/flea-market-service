/*
 * Copyright @ 2021 北京中金付通科技发展有限公司<http://www.ipal.com.cn>. All Rights Reserved.
 */

package com.fleamarket.common.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不需要拦截注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UnAuth {


}
