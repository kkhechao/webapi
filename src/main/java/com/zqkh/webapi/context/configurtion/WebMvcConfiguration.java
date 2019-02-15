package com.zqkh.webapi.context.configurtion;

import com.zqkh.webapi.context.auth.AuthHandlerInterceptor;
import com.zqkh.webapi.context.configurtion.converter.CustomerMappingJackson2HttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by zhaofujun on 2017/8/5.
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Autowired(required = false)
    private AuthHandlerInterceptor authHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        if (authHandlerInterceptor == null) authHandlerInterceptor = new AuthHandlerInterceptor();
        authHandlerInterceptor.addAnonymousPaths(
                "/swagger-resources",
                "/swagger-resources/**",
                "/error",
                "/v2/api-docs*",
                "/configuration/security",
                "/configuration/ui"
        );
        registry.addInterceptor(authHandlerInterceptor);
        super.addInterceptors(registry);
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT","OPTIONS")
                .maxAge(3600);
    }

    public void addAnonymousPaths(String... anonymousPaths) {
        if (authHandlerInterceptor == null) authHandlerInterceptor = new AuthHandlerInterceptor();

        this.authHandlerInterceptor.addAnonymousPaths(anonymousPaths);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(new CustomerMappingJackson2HttpMessageConverter());
    }
}
