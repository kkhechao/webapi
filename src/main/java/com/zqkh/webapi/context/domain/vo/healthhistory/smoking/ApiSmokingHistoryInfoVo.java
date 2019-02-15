package com.zqkh.webapi.context.domain.vo.healthhistory.smoking;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel("吸烟史信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiSmokingHistoryInfoVo implements Serializable {

    /**
     * 家庭成员ID
     */
    @ApiModelProperty(value = "家庭成员ID", name = "familyMemberId", dataType = "String", required = true)
    private String familyMemberId;


}
