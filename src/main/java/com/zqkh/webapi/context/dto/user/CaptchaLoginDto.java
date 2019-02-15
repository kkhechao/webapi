package com.zqkh.webapi.context.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author wenjie
 * @date 2017/12/11 0011 11:51
 */
@Getter
@Setter
public final class CaptchaLoginDto {

    @NotNull(message = "手机号不能为空")
    private String phone;

    @NotNull(message = "验证码不能为空")
    private String captcha;

    private String code;
}
