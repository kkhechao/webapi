package com.zqkh.webapi.context.domain.vo.bracelet;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 呼吸频率
 *
 * @author: zhangchao
 * @time: 2018-07-18 18:53
 **/
@ApiModel(description = "呼吸频率")
@NoArgsConstructor
@Getter
@Setter
public class RespiratoryRateVo implements Serializable {

    /**
     * 用户
     */
    @ApiModelProperty(value = "用户", name = "userId", dataType = "String", required = true)
    private String userId;

    /**
     * 家庭成员
     */
    @ApiModelProperty(value = "家庭成员", name = "familyMemberId", dataType = "String", required = true)
    private String familyMemberId;

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期", name = "date", dataType = "datetime", required = true)
    private Date date;

    /**
     * 设备编号
     */
    @ApiModelProperty(value = "设备编号", name = "deviceId", dataType = "String", required = true)
    private String deviceId;

    /**
     * 呼吸频率
     */
    @ApiModelProperty(value = "呼吸频率", name = "respiratoryRate", dataType = "Integer", required = true)
    private Integer respiratoryRate;


    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", name = "startTime", dataType = "datetime", required = true)
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", name = "endTime", dataType = "datetime", required = true)
    private Date endTime;


}
