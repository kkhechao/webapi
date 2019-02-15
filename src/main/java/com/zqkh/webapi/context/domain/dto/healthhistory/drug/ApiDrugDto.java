package com.zqkh.webapi.context.domain.dto.healthhistory.drug;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.zqkh.webapi.context.domain.enums.ApiMedicationType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("药品信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiDrugDto implements Serializable {

    @ApiModelProperty(value = "Id", name = "id", dataType = "String")
    private String id;

    @ApiModelProperty(value = "药品名称", name = "name", dataType = "String")
    private String name;

    @ApiModelProperty(value = "公司名称", name = "company", dataType = "String")
    private String company;

    @ApiModelProperty(value = "药品图片", name = "icon", dataType = "String")
    private String icon;

    @ApiModelProperty(value = "是否选中", name = "choose", dataType = "Boolean")
    private Boolean choose;

    @ApiModelProperty(value = "药品类型", name = "type", dataType = "ApiMedicationType")
    private ApiMedicationType type;

}

