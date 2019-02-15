package com.zqkh.webapi.context.domain.vo.healthhistory.genetic;

import com.zqkh.webapi.context.domain.enums.ApiDiseaseType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("家族病史信息")
public class ApiGeneticHistoryVo implements Serializable {

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
     * 确诊年龄
     */
    @ApiModelProperty(value = "确诊年龄", name = "diagnoseAges", dataType = "String", required = true)
    private String diagnoseAges;

    /**
     * 亲属关系
     */
    @ApiModelProperty(value = "亲属关系", name = "relation", dataType = "String", required = true)
    private String relation;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", dataType = "String", required = false)
    private String remark;

    /**
     * 疾病
     */
    @ApiModelProperty(value = "疾病", name = "disease", dataType = "String", required = true)
    private String disease;

    /**
     * 疾病类型
     * */
    @ApiModelProperty(value = "疾病类型", name = "diseaseType", dataType = "ApiDiseaseType", required = true)
    private ApiDiseaseType diseaseType;
}
