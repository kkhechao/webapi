package com.zqkh.webapi.context.configurtion.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wenjie
 * @date 2017/12/5 0005 15:24
 */
@Getter
@Component
@ConfigurationProperties(prefix="spring")
public final class CloudConfigProperties {

    private final AndroidVersion androidVersion = new AndroidVersion();
    private final IosVersion iosVersion = new IosVersion();
    private final Redis redis = new Redis();
    private final Jwt jwt = new Jwt();
    private final System system = new System();
    private final Datasource datasource = new Datasource();
    private final AMQ amq = new AMQ();
    private final Bankquery bankquery = new Bankquery();
    private final WeChat weChat = new WeChat();
    private final Ad ad = new Ad();


    @Getter
    @Setter
    public static class WeChat {
        @Value("${appId}")
        private String appId;

        @Value("${secret}")
        private String secret;
    }

    @Setter
    @Getter
    public final static class AndroidVersion{
        @Value("${intro}")
        private  String intro;
        @Value("${latestVersion}")
        private  String latestVersion;
        @Value("${versionCode}")
        private  int versionCode;
        @Value("${mandatoryUpdate}")
        private  Boolean mandatoryUpdate;
        @Value("${url}")
        private  String url;
        @Value("${versionName}")
        private String versionName;
    }

    @Setter
    @Getter
    public final static class IosVersion{
        @Value("${intro}")
        private  String intro;
        @Value("${latestVersion}")
        private  String latestVersion;
        @Value("${versionCode}")
        private  int versionCode;
        @Value("${mandatoryUpdate}")
        private  Boolean mandatoryUpdate;
        @Value("${url}")
        private  String url;
        @Value("${versionName}")
        private String versionName;
    }

    @Getter
    @Setter
    public static class Bankquery{
        @Value("${appCode}")
        private String appCode;
    }

    @Getter
    @Setter
    public static class AMQ{

        @Value("${secretId}")
        private String secretId;
        @Value("${secretKey}")
        private String secretKey;
        @Value("${endpoint}")
        private String endpoint;
    }

    @Getter
    @Setter
    public final static class System {

        @Value("${baseUrl}")
        private String baseUrl;

        @Value("${banks}")
        private List<Bank> banks;

        @Value("${universalCodeSwitch}")
        private Boolean universalCodeSwitch;

        @Value("${universalCode}")
        private String universalCode;

        @Getter
        @Setter
        public final static class Bank {
            private String code;
            private String name;
            private String smallImg;
            private String img;
            private String innerCode;
        }
    }


    @Setter
    @Getter
    public final static class Datasource{

        @Value("${driver-class-name}")
        private String driverClassName;
        @Value("${url}")
        private String url;
        @Value("${username}")
        private String username;
        @Value("${password}")
        private String password;
        @Value("${filters}")
        private String filters;
        @Value("${maxActive}")
        private Integer maxActive;
        @Value("${initialSize}")
        private Integer initialSize;
        @Value("${maxWait}")
        private Integer maxWait;
        @Value("${minIdle}")
        private Integer minIdle;
        @Value("${timeBetweenEvictionRunsMillis}")
        private Integer timeBetweenEvictionRunsMillis;
        @Value("${minEvictableIdleTimeMillis}")
        private Integer minEvictableIdleTimeMillis;
        @Value("${validationQuery}")
        private String validationQuery;
        @Value("${testWhileIdle}")
        private Boolean testWhileIdle;
        @Value("${testOnBorrow}")
        private Boolean testOnBorrow;
        @Value("${testOnReturn}")
        private Boolean testOnReturn;
        @Value("${maxOpenPreparedStatements}")
        private Integer maxOpenPreparedStatements;
        @Value("${removeAbandoned}")
        private Boolean removeAbandoned;
        @Value("${removeAbandonedTimeout}")
        private Integer removeAbandonedTimeout;
        @Value("${logAbandoned}")
        private Boolean logAbandoned;
        @Value("${poolPreparedStatements}")
        private Boolean poolPreparedStatements;
        @Value("${maxPoolPreparedStatementPerConnectionSize}")
        private Integer maxPoolPreparedStatementPerConnectionSize;
    }

    @Setter
    @Getter
    public final static class Redis{
        @Value("${host}")
        private String host;
        @Value("${port}")
        private Integer port;
        @Value("${timeout}")
        private Integer timeout;
        @Value("${password}")
        private String password;
        @Value("${maxActive}")
        private Integer maxActive;
        @Value("${maxWait}")
        private Integer maxWait;
        @Value("${minIdle}")
        private Integer minIdle;
        @Value("${maxIdle}")
        private Integer maxIdle;
        @Value("${testOnBorrow}")
        private Boolean testOnBorrow;
        @Value("${testOnReturn}")
        private Boolean testOnReturn;
    }


    @Setter
    @Getter
    public final static class Jwt{
        //加密
        @Value("${secret}")
        private  String secret;
        //过期时间 默认1周
        @Value("${expiration}")
        private  Long expiration;

    }

    @Getter
    @Setter
    public final static class Ad {
        @Value("${live}")
        private Boolean live;
    }

}
