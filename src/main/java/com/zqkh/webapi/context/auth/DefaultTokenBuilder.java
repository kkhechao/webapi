package com.zqkh.webapi.context.auth;

import com.jovezhao.nest.log.Log;
import com.jovezhao.nest.log.LogAdapter;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.utils.JwtTokenContextUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhaofujun on 2017/5/16.
 */
@Component("DefaultTokenBuilder")
public class DefaultTokenBuilder implements TokenBuilder {
    private Log logger = new LogAdapter(DefaultTokenBuilder.class);
    @Autowired
    private JwtTokenContextUtil jwtTokenContext;

    @Override
    public String getTokenByAuthUser(JWTUserDto authUser) {

        return jwtTokenContext.generateToken(authUser);
    }

    @Override
    public JWTUserDto getAuthUserByToken(String token) {
        try {
            if(StringUtils.isNotBlank(token)){
                final JWTUserDto user = jwtTokenContext.parse(token.trim());
                return  user;
            }
            return null;

        } catch (Exception ex) {
            logger.warn("token解码失败", ex);
            return null;
        }
    }
}