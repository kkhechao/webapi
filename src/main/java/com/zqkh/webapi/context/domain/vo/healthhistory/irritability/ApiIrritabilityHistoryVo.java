package com.zqkh.webapi.context.domain.vo.healthhistory.irritability;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("过敏史信息")
public class ApiIrritabilityHistoryVo implements Serializable {

    @ApiModel("过敏程度")
    public enum Effect {
        BAD,
        SLIGHT
    }

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
     * 过敏原
     */
    @ApiModelProperty(value = "过敏原", name = "allergen", dataType = "String", required = true)
    private String allergen;

    /**
     * 过敏程度
     */
    @ApiModelProperty(value = "过敏程度", name = "effect", dataType = "Effect", required = true)
    private Effect effect;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", dataType = "String", required = false)
    private String remark;

}
