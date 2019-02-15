package com.zqkh.webapi.context.auth;

import com.jovezhao.nest.exception.CustomException;

/**
 * 没有登录时将发生这个异常
 * Created by zhaofujun on 2017/4/18.
 */
public class NoLoginException extends CustomException {
    public NoLoginException(String message, Object... parames) {
        super(403, message, parames);
    }
}