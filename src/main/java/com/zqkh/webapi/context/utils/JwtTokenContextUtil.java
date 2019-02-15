package com.zqkh.webapi.context.utils;

import com.zqkh.webapi.context.configurtion.properties.CloudConfigProperties;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("jwtTokenContext")
public class JwtTokenContextUtil implements Serializable {

    @Autowired
    private CloudConfigProperties cloudConfigProperties;

    private static final long serialVersionUID = -3301605591108950415L;

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";


    private String header="Authorization";



    public String getUserIdFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(cloudConfigProperties.getJwt().getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + cloudConfigProperties.getJwt().getExpiration() * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token) {

        return (!isTokenExpired(token));
    }



    /**
     * 从token中取出用户
     */
    public JWTUserDto parse(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(cloudConfigProperties.getJwt().getSecret())
                .parseClaimsJws(token)
                .getBody();
        JWTUserDto userDTO = new JWTUserDto();
        userDTO.setId(claims.get("id",String.class));
        userDTO.setAvatar(claims.get("avatar",String.class));
        userDTO.setNickName(claims.get("nickName",String.class));
        userDTO.setCode(claims.get("code",String.class));
        userDTO.setMobile(claims.get("mobile",String.class));
        userDTO.setUserId(claims.get("userId", String.class));
        userDTO.setAccountId(claims.get("accountId", String.class));
        userDTO.setRoles((List<String>) claims.get("roles"));
        return userDTO;
    }






    /**
     * 从用户中创建一个jwt Token
     *
     * @param userDto 用户
     */
    public String generateToken(JWTUserDto userDto) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDto.getId());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put("id", userDto.getId());
        claims.put("avatar", userDto.getId());
        claims.put("code", userDto.getCode());
        claims.put("mobile", userDto.getMobile());
        claims.put("nickName", userDto.getNickName());
        claims.put("roles", userDto.getRoles());
        claims.put("userId", userDto.getUserId());
        claims.put("accountId", userDto.getAccountId());
        return generateToken(claims);
    }

    String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, cloudConfigProperties.getJwt().getSecret())
                .compact();
    }




    /**
     * 验签
     */
    public JWTUserDto verifyToken(HttpServletRequest request) {
        final String token = request.getHeader(header);
        if (token != null && !token.isEmpty()){
            final JWTUserDto user = parse(token.trim());
            if (user != null) {
                return user;
            }
        }

        return null;
    }

}

