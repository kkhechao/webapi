package com.zqkh.webapi.context.domain.dto.healthhistory.takehorn;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 饮酒信息 API 响应 DTO
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("饮酒信息")
public class ApiTakeHornInfoDto implements Serializable {

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
     * 饮酒量
     */
    @ApiModelProperty(value = "饮酒量", name = "takeHornQuantity", dataType = "Integer")
    private Integer takeHornQuantity;

}
