package com.zqkh.webapi.context.configurtion;

import com.zqkh.webapi.context.utils.JwtTokenContextUtil;
import com.zqkh.webapi.context.verifyidcard.AiyunIdCardStrategyImpl;
import com.zqkh.webapi.context.verifyidcard.VerificationContext;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class CommonConfiguration {

    @Bean
    public JwtTokenContextUtil configToken() {
        return new JwtTokenContextUtil();
    }

    @Bean
    public VerificationContext verificationContext() {

        return new VerificationContext(new AiyunIdCardStrategyImpl());
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(1024L * 1024L*1024L);
        factory.setMaxRequestSize(1024L*1024L*1024L);
        return factory.createMultipartConfig();
    }

}
