package com.zqkh.webapi.context.domain.vo.healthhistory.drug;

import com.zqkh.webapi.context.domain.vo.healthhistory.PageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("药品信息")
@EqualsAndHashCode(callSuper = true)
public class ApiDrugVo extends PageVo implements Serializable {

    @ApiModelProperty(value = "疾病Id", name = "symptomId", dataType = "String")
    private String symptomId;

    @ApiModelProperty(value = "药品id列表", name = "drugs", dataType = "String")
    private List<ApiDrugItem> drugs;
}
