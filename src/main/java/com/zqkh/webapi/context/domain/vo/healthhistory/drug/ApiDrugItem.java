package com.zqkh.webapi.context.domain.vo.healthhistory.drug;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zqkh.webapi.context.domain.enums.ApiMedicationType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@ApiModel("药品信息")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ApiDrugItem implements Serializable {

    @ApiModelProperty(value = "药品Id", name = "id", dataType = "String")
    private String id;

    @ApiModelProperty(value = "药品名称", name = "name", dataType = "String")
    private String name;

    @ApiModelProperty(value = "药品类型", name = "type", dataType = "ApiMedicationType")
    private ApiMedicationType type;

}