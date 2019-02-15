package com.zqkh.webapi.context.domain.vo.test.paper;

import io.swagger.annotations.ApiModel;
import org.springframework.util.ObjectUtils;

@ApiModel(description = "试卷类型")
public enum TestPaperTypeEnumVo {
    /**
     * 疾病自测
     */
    DISEASE,
    /**
     * 健康自测
     */
    HEALTHY;

    private TestPaperTypeEnumVo() {
    }

    public static TestPaperTypeEnumVo getTestPaperTypeEnum(String name) {
        if (ObjectUtils.isEmpty(name)) {
            return null;
        }

        for (TestPaperTypeEnumVo testPaperTypeEnum : TestPaperTypeEnumVo.values()) {
            if (testPaperTypeEnum.name().equals(name)) {
                return testPaperTypeEnum;

            }

        }
        return null;

    }
}
