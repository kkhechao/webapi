package com.zqkh.webapi.context.dto.healthy.disease.self.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 疾病自测列表
 *
 * @author 东来
 * @create 2018/5/9 0009
 */
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "疾病自测列表")
public class DiseaseSelfTestListDto implements Serializable {

    /**
     * 疾病名称
     */
    @ApiModelProperty(value = "疾病名称", name = "name", dataType = "String", required = true)
    private String name;

    /**
     * 是否已完成
     */
    @ApiModelProperty(value = "是否已完成", name = "done", dataType = "boolean", required = true)
    private boolean done = false;

    /**
     * 当前疾病风险
     */
    @ApiModelProperty(value = "当前疾病风险", name = "risk", dataType = "Risk", required = true)
    private Risk risk = Risk.UNKNOWN;

    /**
     * 当前疾病风险
     */
    @ApiModel(value = "疾病风险")
    public enum Risk {
        /**
         * 高
         */
        HIGH,
        /**
         * 低
         */
        LOW,
        /**
         * 中
         */
        MIDDLE,
        /**
         * 正常
         */
        NORMAL,

        /**
         * 未知
         */
        UNKNOWN,;

        public static Risk getRisk(String riskName) {
            for (Risk risk : Risk.values()) {
                if (risk.name().equals(riskName)) {
                    return risk;
                }
            }
            return UNKNOWN;
        }
    }

}
