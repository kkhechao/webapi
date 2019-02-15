package com.zqkh.webapi.context.domain.vo.healthhistory.smoking;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("吸烟信息")
@Data
public class ApiSmokingInfoVo implements Serializable {

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
     * 吸烟量
     */
    @ApiModelProperty(value = "吸烟量", name = "smokingQuantity", dataType = "Integer", required = true)
    private Integer smokingQuantity;
}
