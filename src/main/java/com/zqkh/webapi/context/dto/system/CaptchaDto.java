package com.zqkh.webapi.context.dto.system;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author wenjie
 * @date 2017/12/8 0008 15:56
 */
@Getter
@Setter
public final class CaptchaDto {

    public enum Type{
        LOGIN,
        WITHDRAW,
        CHECK_OLD_PHONE,
        BIND_NEW_PHONE
    }

    public enum Format{
        TXT,
        VOICE
    }
    private Format format = Format.TXT;

    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^1\\d{10}$", message = "手机号格式错误")
    private String phone;

    @NotNull(message = "验证码类型不能为空")
    private Type type;
}
