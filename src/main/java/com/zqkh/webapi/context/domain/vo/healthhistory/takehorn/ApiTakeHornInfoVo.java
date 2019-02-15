package com.zqkh.webapi.context.domain.vo.healthhistory.takehorn;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("饮酒信息")
@Data
public class ApiTakeHornInfoVo implements Serializable {

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
     * 饮酒量
     */
    @ApiModelProperty(value = "饮酒量", name = "takeHornQuantity", dataType = "Integer", required = true)
    private Integer takeHornQuantity;
}
