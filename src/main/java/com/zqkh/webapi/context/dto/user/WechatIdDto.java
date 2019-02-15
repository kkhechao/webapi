package com.zqkh.webapi.context.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WechatIdDto {

    private String openId;

    private String unionId;

    private String avatar;

    private String nickName;
}
