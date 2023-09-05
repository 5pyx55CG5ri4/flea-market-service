package com.fleamarket.common.annotation;


import cn.hutool.http.HttpUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TranslationValue {
    String targetTable();


    String translationField();

    String alias();

    String whereField();





}
