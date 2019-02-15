package com.zqkh.webapi.context.domain.dto.healthhistory.diet;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel(description = "饮食史列表")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiDietHistoryDto implements Serializable {

    /**
     * 用餐类型
     */
    public enum MealType {
        BREAKFAST,
        LUNCH,
        DINNER
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
     * 图片列表
     */
    @ApiModelProperty(value = "图片列表", name = "imgUrls", dataType = "String")
    private List<String> imgUrls;

    /**
     * 用餐类型
     */
    @ApiModelProperty(value = "用餐类型", name = "type", dataType = "MealType")
    private MealType type;

    /**
     * 用餐时间
     */
    @ApiModelProperty(value = "用餐时间", name = "eatTime", dataType = "Date")
    private Date eatTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", dataType = "String")
    private String remark;
}
