package com.zqkh.webapi.context.verifyidcard;

import java.io.UnsupportedEncodingException;

/**
 * 身份证验证策略
 */
public interface VerifyIDcardStrategy {

    String validation(RealNameAuth realNameAuth) throws UnsupportedEncodingException;
}
