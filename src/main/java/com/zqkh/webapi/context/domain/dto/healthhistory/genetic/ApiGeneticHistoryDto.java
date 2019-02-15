package com.zqkh.webapi.context.domain.dto.healthhistory.genetic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zqkh.webapi.context.domain.enums.ApiDiseaseType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 家族病史响应  DTO
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "家族病史")
public class ApiGeneticHistoryDto implements Serializable {

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
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime", dataType = "Date")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", name = "updateTime", dataType = "Date")
    private Date updateTime;

    /**
     * 确诊年龄
     */
    @ApiModelProperty(value = "确诊年龄", name = "diagnoseAges", dataType = "String")
    private String diagnoseAges;

    /**
     * 亲属关系
     */
    @ApiModelProperty(value = "亲属关系", name = "relation", dataType = "String")
    private String relation;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", dataType = "String")
    private String remark;

    /**
     * 疾病
     */
    @ApiModelProperty(value = "疾病ID", name = "disease", dataType = "String")
    private String disease;

    /**
     * 疾病名称
     */
    @ApiModelProperty(value = "疾病名称", name = "disease", dataType = "String")
    private String diseaseName;

    /**
     * 疾病类型
     */
    @ApiModelProperty(value = "疾病类型", name = "diseaseType", dataType = "ApiDiseaseType")
    private ApiDiseaseType diseaseType;

    /**
     * 部位Key
     * */
    @ApiModelProperty(value = "部位Key", name = "placeName", dataType = "String")
    private String placeName;
}
