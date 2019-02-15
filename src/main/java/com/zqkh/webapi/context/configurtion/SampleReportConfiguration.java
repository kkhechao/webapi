package com.zqkh.webapi.context.configurtion;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 示例报告配置
 *
 * @author 东来
 * @create 2018/7/3 0003
 */
@Getter
@Component
@ConfigurationProperties(prefix = "sample")
public class SampleReportConfiguration {

    private final Woman woman = new Woman();

    private final Man man = new Man();

    @Setter
    @Getter
    public static class Woman {
        @Value("woman-geneOrderId")
        private String geneOrderId;
    }

    @Setter
    @Getter
    public static class Man {
        @Value("man-geneOrderId")
        private String geneOrderId;
    }

}
