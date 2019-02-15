package com.zqkh.webapi.context.jwt.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class JWTUserDto implements Serializable {
    private String id;
    private String userId;
    private String nickName;
    private String code;
    private String mobile;
    private String avatar;
    private List<String> roles;
    private Date createTime;
    private String accountId;
}
