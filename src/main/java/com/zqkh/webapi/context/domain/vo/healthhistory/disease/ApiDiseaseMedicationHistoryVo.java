package com.zqkh.webapi.context.domain.vo.healthhistory.disease;

import com.zqkh.webapi.context.domain.enums.ApiDiseaseType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel("添加和修改疾病用药史")
@Data
public class ApiDiseaseMedicationHistoryVo implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "id", dataType = "String", required = false)
    private String id;

    /**
     * 家庭成员ID
     */
    @ApiModelProperty(value = "家庭成员ID", name = "familyMemberId", dataType = "String", required = true)
    private String familyMemberId;

    /**
     * 患病时间
     */
    @ApiModelProperty(value = "患病时间", name = "sickTime", dataType = "Date", required = true)
    private Date sickTime;

    /**
     * 疾病 ID
     */
    @ApiModelProperty(value = "疾病 ID", name = "disease", dataType = "String", required = true)
    private String disease;

    /**
     * 用药史
     */
    @ApiModelProperty(value = "用药史", name = "medicationHistory", dataType = "ApiMedicationHistoryVo", required = true)
    private ApiMedicationHistoryVo medicationHistory;

    /**
     * 是否痊愈
     */
    @ApiModelProperty(value = "是否痊愈", name = "cured", dataType = "Boolean", required = true)
    private Boolean cured;


    /**
     * 疾病备注
     */
    @ApiModelProperty(value = "疾病备注", name = "remark", dataType = "String", required = false)
    private String remark;

    /**
     * 疾病类型
     * */
    @ApiModelProperty(value = "疾病类型", name = "diseaseType", dataType = "ApiDiseaseType", required = true)
    private ApiDiseaseType diseaseType;
}
