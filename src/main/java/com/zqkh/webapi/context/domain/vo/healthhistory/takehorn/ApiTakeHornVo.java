package com.zqkh.webapi.context.domain.vo.healthhistory.takehorn;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;


@ApiModel("饮酒史信息")
@Data
public class ApiTakeHornVo implements Serializable {

    /**
     * 戒酒状态
     */
    @ApiModel("戒酒状态")
    public enum Status {
        QUIT_HORN,
        KEEP_HORN,
        INTEND;

        public static Status getStatus(String name) {
            for (Status item : Status.values()) {
                if (Objects.equals(item.name(), name)) {
                    return item;
                }
            }
            return null;
        }
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
     * 酒龄
     */
    @ApiModelProperty(value = "酒龄", name = "takeHornYears", dataType = "Integer", required = true)
    private Integer takeHornYears;

    /**
     * 戒酒年限
     */
    @ApiModelProperty(value = "戒酒年限", name = "takeHornCessationYears", dataType = "Integer", required = false)
    private Integer takeHornCessationYears;

    /**
     * 戒酒状态
     */
    @ApiModelProperty(value = "戒酒状态", name = "status", dataType = "Status", required = true)
    private Status status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", dataType = "String", required = false)
    private String remark;

}
