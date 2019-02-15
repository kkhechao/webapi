package com.zqkh.webapi.context.domain.dto.healthhistory.takehorn;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 饮酒史 API 响应 DTO
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("饮酒史信息")
public class ApiTakeHornDto implements Serializable {
    /**
     * 戒酒状态
     */
    public enum Status {
        QUIT_HORN,
        KEEP_HORN,
        INTEND;
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
     * 酒龄
     */
    @ApiModelProperty(value = "酒龄", name = "takeHornYears", dataType = "Integer")
    private Integer takeHornYears;

    /**
     * 戒酒年限
     */
    @ApiModelProperty(value = "戒酒年限", name = "takeHornCessationYears", dataType = "Integer")
    private Integer takeHornCessationYears;

    /**
     * 戒酒状态
     */
    @ApiModelProperty(value = "戒酒状态", name = "status", dataType = "Status")
    private Status status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", dataType = "String")
    private String remark;

    @ApiModelProperty(value = "饮酒数据(图表的数据)", name = "takeHornInfoList", dataType = "ApiTakeHornInfoDto")
    private List<ApiTakeHornInfoDto> takeHornInfoList;
}
