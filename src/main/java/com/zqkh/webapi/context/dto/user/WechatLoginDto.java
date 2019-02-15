package com.zqkh.webapi.context.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class WechatLoginDto {

    @NotNull(message = "手机号不能为空")
    private String phone;

    @NotNull(message = "验证码不能为空")
    private String captcha;

    private String unionId;

    private String openId;

    private String avatar;

    private String nickName;
}
