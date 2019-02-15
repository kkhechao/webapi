package com.zqkh.webapi;

import com.zqkh.system.sdk.annotation.EnableSystemSdk;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@RefreshScope
@EnableSystemSdk
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.zqkh.*.feign"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBeanV1() {
        AnnotationConfigWebApplicationContext applicationContext
                = new AnnotationConfigWebApplicationContext();
        //base package
        applicationContext.scan("com.zqkh.webapi.web.v1");
        //通过构造函数指定dispatcherServlet的上下文
        DispatcherServlet rest_dispatcherServlet
                = new DispatcherServlet(applicationContext);

        //用ServletRegistrationBean包装servlet
        ServletRegistrationBean registrationBean
                = new ServletRegistrationBean(rest_dispatcherServlet);
        registrationBean.setLoadOnStartup(1);
        //指定urlmapping
        registrationBean.addUrlMappings("/v1.0/*");
        //指定name，如果不指定默认为dispatcherServlet
        registrationBean.setName("v1.0");
        return registrationBean;
    }


//    @Bean
//    public CommonsMultipartResolver multipartResolver(){
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        resolver.setMaxUploadSize(1024*1024*1024); // set the size limit
//        return resolver;
//    }
}
