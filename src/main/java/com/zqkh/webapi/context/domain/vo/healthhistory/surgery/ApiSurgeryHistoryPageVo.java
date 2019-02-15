package com.zqkh.webapi.context.domain.vo.healthhistory.surgery;

import com.zqkh.webapi.context.domain.vo.healthhistory.PageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@ApiModel("分页获取手术史信息")
@Data
public class ApiSurgeryHistoryPageVo extends PageVo {

    /**
     * 家庭成员ID
     */
    @ApiModelProperty(value = "家庭成员ID", name = "familyMemberId", dataType = "String", required = true)
    private String familyMemberId;


}
