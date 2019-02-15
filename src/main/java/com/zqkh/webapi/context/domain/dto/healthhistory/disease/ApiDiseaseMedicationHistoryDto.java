package com.zqkh.webapi.context.domain.dto.healthhistory.disease;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zqkh.webapi.context.domain.enums.ApiDiseaseType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "疾病用药史列表")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiDiseaseMedicationHistoryDto implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "id", dataType = "String")
    private String id;

    /**
     * 家庭成员ID
     */
    @ApiModelProperty(value = "家庭成员ID", name = "familyMemberId", dataType = "String")
    private String familyMemberId;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", name = "userId", dataType = "String")
    private String userId;
    /**
     * 患病时间
     */
    @ApiModelProperty(value = "患病时间", name = "sickTime", dataType = "Date")
    private Date sickTime;
    /**
     * 疾病 ID
     */
    @ApiModelProperty(value = "疾病 ID", name = "disease", dataType = "String")
    private String disease;

    /**
     * 疾病名称
     */
    @ApiModelProperty(value = "疾病名称", name = "diseaseName", dataType = "String")
    private String diseaseName;

    /**
     * 部位Key
     * */
    @ApiModelProperty(value = "部位Key", name = "placeName", dataType = "String")
    private String placeName;

    /**
     * 用药史
     */
    @ApiModelProperty(value = "用药史", name = "medicationHistory", dataType = "ApiMedicationHistoryDto")
    private ApiMedicationHistoryDto medicationHistory;
    /**
     * 是否痊愈
     */
    @ApiModelProperty(value = "是否痊愈", name = "cured", dataType = "Boolean")
    private Boolean cured;
    /**
     * 疾病备注
     */
    @ApiModelProperty(value = "疾病备注", name = "remark", dataType = "String")
    private String remark;

    /**
     * 疾病类型
     * */
    @ApiModelProperty(value = "疾病类型", name = "diseaseType", dataType = "ApiDiseaseType")
    private ApiDiseaseType diseaseType;

}
