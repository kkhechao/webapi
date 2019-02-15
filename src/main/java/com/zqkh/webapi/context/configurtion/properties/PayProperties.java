package com.zqkh.webapi.context.configurtion.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by wenjie on 2016/7/9.
 */
@Component
@ConfigurationProperties(prefix = "pay")
@Getter
@Setter
@Slf4j
public class PayProperties {

    private final Alipay alipay = new Alipay();
    private final Wechat wechat = new Wechat();

    @Getter
    @Setter
    public static class Alipay {
        private String notifyUrl;
        private String returnUrl;
        private String showUrl;
        private String gatewayUrl;
        private String appId;
        private String privateKey;
        private String publicKey;
    }

    @Getter
    @Setter
    public static class Wechat {
        private String appId;
        private String mchId;
        private String key;
        private String appSecret;
        private String notifyUrl;
        private String requestUrl;
    }
}
