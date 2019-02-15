package com.zqkh.webapi.context.domain.dto.healthhistory.irritability;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 过敏史 API 响应 Dto
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("过敏史信息")
public class ApiIrritabilityHistoryDto implements Serializable {

    /**
     * 过敏程度
     */
    @ApiModel("过敏程度")
    public enum Effect {
        BAD,
        SLIGHT
    }

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
     * 过敏原
     */
    @ApiModelProperty(value = "过敏原", name = "allergen", dataType = "String")
    private String allergen;

    /**
     * 过敏程度
     */
    @ApiModelProperty(value = "过敏程度", name = "effect", dataType = "Effect")
    private Effect effect;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", dataType = "String")
    private String remark;

}
