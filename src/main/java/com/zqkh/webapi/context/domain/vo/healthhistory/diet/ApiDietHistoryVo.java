package com.zqkh.webapi.context.domain.vo.healthhistory.diet;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel("添加饮食史")
@Data
public class ApiDietHistoryVo implements Serializable {

    /**
     * 用餐类型
     */
    @ApiModel("用餐类型")
    public enum MealType {
        BREAKFAST,
        LUNCH,
        DINNER
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
     * 图片列表
     */
    @ApiModelProperty(value = "图片列表", name = "imgUrls", dataType = "String", required = true)
    private List<String> imgUrls;

    /**
     * 用餐类型
     */
    @ApiModelProperty(value = "用餐类型", name = "type", dataType = "MealType", required = true)
    private MealType type;

    /**
     * 用餐时间
     */
    @ApiModelProperty(value = "用餐时间", name = "eatTime", dataType = "Date", required = true)
    private Date eatTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", dataType = "String", required = false)
    private String remark;

}
