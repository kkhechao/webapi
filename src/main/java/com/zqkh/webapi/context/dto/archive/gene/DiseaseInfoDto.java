package com.zqkh.webapi.context.dto.archive.gene;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.zqkh.webapi.context.dto.archive.disease.DetectionOfOverviewDto;
import com.zqkh.webapi.context.dto.archive.disease.ExaminationDto;
import com.zqkh.webapi.context.dto.archive.disease.HealthAdviceDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 基因疾病信息
 *
 * @author 东来
 * @create 2018/3/2 0002
 */
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "疾病详情")
public class DiseaseInfoDto  implements Serializable {

    @ApiModel(value = "疾病风险")
    public enum Risk{
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
        NORMAL;

        public static DiseaseInfoDto.Risk getRisk(String riskName){
            for (DiseaseInfoDto.Risk risk: DiseaseInfoDto.Risk.values()) {
                if(risk.name().equals(riskName)){
                    return risk;
                }
            }
            return null;
        }
    }

    /**
     * 疾病名
     */
    @ApiModelProperty(value = "疾病名", name = "name", required = true)
    private String name;

    /**
     * 英语名
     */
    @ApiModelProperty(value = "疾病英文名", name = "englishName", required = true)
    private String englishName;

    /**
     * 疾病风险
     */
    @ApiModelProperty(value = "疾病风险", name = "risk", required = true)
    private Risk risk;

    /**
     * 疾病图标
     */
    @ApiModelProperty(value = "疾病图标", name = "icoUrl", required = true)
    private String icoUrl;


    /**
     * 检测位点
     */
    @ApiModelProperty(value = "检测位点", name = "locusInfo", required = true)
    private List<LocusInfoDto> locusInfo;


    /**
     * 症状
     */
    @ApiModelProperty(value = "症状", name = "symptom")
    private List<String> symptom;

    /**
     * 涉及相关检查
     */
    @ApiModelProperty(value = "涉及相关检查", name = "examination")
    private List<ExaminationDto> examination;

    /**
     * 预防保健以及建议
     */
    @ApiModelProperty(value = "预防保健以及建议", name = "healthAdvice")
    private List<HealthAdviceDto> healthAdvice;

    /**
     * 检测概述
     */
    @ApiModelProperty(value = "检测概述", name = "detectionOfOverview", dataType = "DetectionOfOverviewDto")
    private DetectionOfOverviewDto detectionOfOverview;


    /**
     * 参考文献
     */
    @ApiModelProperty(value = "参考文献", name = "reference")
    private String reference;

    /**
     * 疾病描述
     */
    @ApiModelProperty(value = "疾病描述", name = "desc")
    private String desc;

}
