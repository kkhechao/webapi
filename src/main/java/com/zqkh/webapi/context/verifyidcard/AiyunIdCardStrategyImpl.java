package com.zqkh.webapi.context.verifyidcard;

import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import lombok.extern.log4j.Log4j;

import java.io.UnsupportedEncodingException;

@Log4j
public class AiyunIdCardStrategyImpl implements VerifyIDcardStrategy {
    //响应状态码，大于等于200小于300表示成功；大于等于400小于500为客户端错误；大于500为服务端错误。
    public static final int STATUSCODE = 200;
    public static final int OKSCODE = 300;
    public static final String OK = "0";
    private SyncApiClientRealNameAuth syncClient = null;
    //APP KEY
    private final static String APP_KEY = "24835834";
    // APP密钥
    private final static String APP_SECRET = "4026ec23be1f184702122ad6c63f561a";

    public AiyunIdCardStrategyImpl() {
        this.syncClient = SyncApiClientRealNameAuth.newBuilder()
                .appKey(APP_KEY)
                .appSecret(APP_SECRET)
                .build();
    }

    @Override
    public String validation(RealNameAuth realNameAuth) throws UnsupportedEncodingException {

        ApiResponse response = syncClient.validation(realNameAuth.getIdCard(), realNameAuth.getName());
        printResponse(response);
        int statusCode = response.getStatusCode();
        log.info("身份证实名验证返回状态码{}" + statusCode);
        return new String(response.getBody(), "utf-8");
    }

    private static void printResponse(ApiResponse response) {
        try {
            log.info("response code = " + response.getStatusCode());
            log.info("response content = " + new String(response.getBody(), "utf-8"));
            log.info("response headers=" + response.getHeaders().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
