package com.zqkh.webapi.context.configurtion.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hty
 * @create 2018-03-07 15:58
 **/
@Component
@ConfigurationProperties(prefix = "live")
@Getter
@Setter
public class LiveProperties {
    private String regionId;
    private String accessKeyId;
    private String secret;
    private String domainName;
    private String appName;
    private String actionName;
}
