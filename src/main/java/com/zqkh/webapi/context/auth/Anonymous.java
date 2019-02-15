package com.zqkh.webapi.context.auth;

import java.lang.annotation.*;

/**
 * 指定方法或controll允许匿名访问
 * Created by zhaofujun on 2017/4/18.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Anonymous {
    /**
     * 默认为false，表示不验证，当指定为true是任然验证
     * @return
     */
    boolean value() default true;
}