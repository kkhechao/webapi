package com.zqkh.webapi.context.configurtion;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 疾病名称配置
 *
 * @author 东来
 * @create 2018/5/9 0009
 */
@Getter
@Component
@ConfigurationProperties(prefix = "disease")
public class DiseaseNameConfiguration {

    @Resource
    private DiseaseNameConfiguration diseaseNameConfiguration;

    private final Woman woman = new Woman();

    private final Man man = new Man();

    private final Currency currency = new Currency();

    @Getter
    @Setter
    public static class Currency {
        @Value("currency-name")
        private List<String> name;
    }


    @Setter
    @Getter
    public static class Woman {
        @Value("woman-name")
        private List<String> name;
    }

    @Setter
    @Getter
    public static class Man {
        @Value("man-name")
        private List<String> name;
    }


}
