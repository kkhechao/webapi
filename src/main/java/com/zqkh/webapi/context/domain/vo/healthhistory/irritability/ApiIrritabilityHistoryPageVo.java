package com.zqkh.webapi.context.domain.vo.healthhistory.irritability;

import com.zqkh.webapi.context.domain.vo.healthhistory.PageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 过敏史 API 分页 Vo
 */
@Data
@ApiModel("分页获取过敏史信息")
@EqualsAndHashCode(callSuper = true)
public class ApiIrritabilityHistoryPageVo extends PageVo {


    /**
     * 家庭成员ID
     */
    @ApiModelProperty(value = "家庭成员ID", name = "familyMemberId", dataType = "String", required = true)
    private String familyMemberId;

}
