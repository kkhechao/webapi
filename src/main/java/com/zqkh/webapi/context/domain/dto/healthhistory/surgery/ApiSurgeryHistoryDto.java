package com.zqkh.webapi.context.domain.dto.healthhistory.surgery;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zqkh.webapi.context.domain.enums.ApiDiseaseType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "手术史信息")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiSurgeryHistoryDto implements Serializable {

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
     * 手术时间
     */
    @ApiModelProperty(value = "手术时间", name = "surgeryTime", dataType = "Date")
    private Date surgeryTime;


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
     * 手术原因
     */
    @ApiModelProperty(value = "手术原因", name = "disease", dataType = "String")
    private String disease;

    /**
     * 疾病名称
     */
    @ApiModelProperty(value = "疾病名称", name = "diseaseName", dataType = "String")
    private String diseaseName;

    /**
     * 疾病类型
     */
    @ApiModelProperty(value = "疾病类型", name = "diseaseType", dataType = "String")
    private ApiDiseaseType diseaseType;

    /**
     * 部位Key
     * */
    @ApiModelProperty(value = "部位Key", name = "placeName", dataType = "String")
    private String placeName;

}
