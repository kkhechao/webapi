package com.zqkh.webapi.context.auth;

import com.zqkh.webapi.context.jwt.dto.JWTUserDto;

/**
 * Created by zhaofujun on 2017/7/31.
 */
public interface TokenBuilder {
    String getTokenByAuthUser(JWTUserDto authUser);
    JWTUserDto getAuthUserByToken(String token);
}