package com.zqkh.webapi.context.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RealNameAuthDto {

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空")
    private String name;

    /**
     * 身份证号
     */
    @NotNull(message = "身份证号不能为空")
    private String idCard;

    /**
     * 身份证正面
     */
    //@NotNull(message = "身份证正面图片不能为空")
    private String cardFront;

    /**
     * 身份证反面
     */
    //@NotNull(message = "身份证反面图片不能为空")
    private String cardBack;
}
