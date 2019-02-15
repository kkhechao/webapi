package com.zqkh.webapi.context.auth;

import com.jovezhao.nest.utils.SpringUtils;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;

/**
 * Created by zhaofujun on 2017/7/31.
 */
public class AuthManager {
    private static ThreadLocal<JWTUserDto> threadLocal = new ThreadLocal<>();

    public static JWTUserDto currentUser() {
        return threadLocal.get();
    }

    static void setUser(JWTUserDto authUser) {
        threadLocal.set(authUser);
    }

    public static TokenBuilder getTokenBuilder() {
        TokenBuilder tokenBuilder = SpringUtils.getInstance(TokenBuilder.class);
        return tokenBuilder;
    }

}