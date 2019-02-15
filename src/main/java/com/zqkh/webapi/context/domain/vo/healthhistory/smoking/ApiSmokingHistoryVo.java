package com.zqkh.webapi.context.domain.vo.healthhistory.smoking;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("吸烟史信息")
@Data
public class ApiSmokingHistoryVo implements Serializable {

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
    @ApiModelProperty(value = "主键", name = "id", dataType = "String", required = false)
    private String id;

    /**
     * 家庭成员ID
     */
    @ApiModelProperty(value = "家庭成员ID", name = "familyMemberId", dataType = "String", required = true)
    private String familyMemberId;

    /**
     * 烟龄
     */
    @ApiModelProperty(value = "烟龄", name = "smokingYears", dataType = "Integer", required = true)
    private Integer smokingYears;

    /**
     * 戒烟年限
     */
    @ApiModelProperty(value = "戒烟年限", name = "smokingCessationYears", dataType = "Integer", required = false)
    private Integer smokingCessationYears;

    /**
     * 戒烟状态
     */
    @ApiModelProperty(value = "戒烟状态", name = "status", dataType = "Status", required = true)
    private Status status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", dataType = "String", required = false)
    private String remark;

}
