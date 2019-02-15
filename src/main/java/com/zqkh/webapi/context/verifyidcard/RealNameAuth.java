package com.zqkh.webapi.context.verifyidcard;

import lombok.Getter;
import lombok.Setter;

/**
 * 实名认证
 */
@Setter
@Getter
public class RealNameAuth {

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 身份证正面
     */
    private String cardFront;

    /**
     * 身份证反面
     */
    private String cardBack;

    public RealNameAuth(String name, String idCard, String cardFront, String cardBack) {
        this.name = name;
        this.idCard = idCard;
        this.cardFront = cardFront;
        this.cardBack = cardBack;
    }
}
