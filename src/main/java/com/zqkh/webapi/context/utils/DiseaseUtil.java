package com.zqkh.webapi.context.utils;

import com.zqkh.webapi.context.configurtion.DiseaseNameConfiguration;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 疾病工具
 *
 * @author 东来
 * @create 2018/5/10 0010
 */
public class DiseaseUtil {

    /**
     * 获取所有疾病名称
     *
     * @param diseaseNameConfiguration
     * @param gender
     * @return
     */
    public static List<String> getDiseaseName(DiseaseNameConfiguration diseaseNameConfiguration, String gender) {
        List<String> result = new ArrayList<>();
        if (ObjectUtils.isEmpty(gender)) {
            gender = "";
        }
        switch (gender) {
            case "WOMAN":
                result.addAll(diseaseNameConfiguration.getWoman().getName());
                break;
            case "MAN":
                result.addAll(diseaseNameConfiguration.getMan().getName());
                break;
            default:
                result.addAll(diseaseNameConfiguration.getWoman().getName());
                result.addAll(diseaseNameConfiguration.getMan().getName());
                break;
        }
        result.addAll(diseaseNameConfiguration.getCurrency().getName());
        return result;
    }
}
