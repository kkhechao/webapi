package com.zqkh.webapi.context.domain.dto.healthhistory.smoking;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 吸烟史 API 响应 DTO
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("吸烟史信息")
public class ApiSmokingHistoryDto implements Serializable {

    /**
     * 吸烟状态
     */
    @ApiModel("吸烟状态")
    public enum Status {
        /**
         * 已戒烟
         */
        QUIT_SMOKING,
        /**
         * 未戒烟
         */
        KEEP_SMOKING,
        /**
         * 想要戒酒
         */
        INTEND
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
     * 烟龄
     */
    @ApiModelProperty(value = "烟龄", name = "smokingYears", dataType = "Integer")
    private Integer smokingYears;

    /**
     * 戒烟年限
     */
    @ApiModelProperty(value = "戒烟年限", name = "smokingCessationYears", dataType = "Integer")
    private Integer smokingCessationYears;

    /**
     * 戒烟状态
     */
    @ApiModelProperty(value = "戒烟状态", name = "status", dataType = "Status")
    private Status status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", dataType = "String")
    private String remark;

    @ApiModelProperty(value = "吸烟数据(图表的数据)", name = "smokingInfoList", dataType = "ApiSmokingInfoDto")
    private List<ApiSmokingInfoDto> smokingInfoList;
}
