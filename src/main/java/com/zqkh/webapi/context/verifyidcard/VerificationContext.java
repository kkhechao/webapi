package com.zqkh.webapi.context.verifyidcard;

import java.io.UnsupportedEncodingException;

/**
 * 身份验证
 */

public class VerificationContext {

    private VerifyIDcardStrategy idCardStrategy;

    public VerificationContext(VerifyIDcardStrategy idCardStrategy) {
        this.idCardStrategy = idCardStrategy;
    }

    public String authenticationRealName(RealNameAuth realNameAuth) throws UnsupportedEncodingException {
        return idCardStrategy.validation(realNameAuth);
    }

}
