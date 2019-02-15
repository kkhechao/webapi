package com.zqkh.webapi.context.domain.vo.healthhistory.genetic;

import com.zqkh.webapi.context.domain.vo.healthhistory.PageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 家族病史 分页 VO
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel("分页获取家族病史信息")
@Data
public class ApiGeneticHistoryPageVo extends PageVo {

    /**
     * 家庭成员ID
     */
    @ApiModelProperty(value = "家庭成员ID", name = "familyMemberId", dataType = "String", required = true)
    private String familyMemberId;


}
