package com.zqkh.webapi.context.domain.dto.healthhistory.symptom;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "疾病信息")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiSymptomDto implements Serializable {


    /**
     * ID
     */
    @ApiModelProperty(value = "ID", name = "id", dataType = "String")
    private String id;

    /**
     * 疾病名称
     */
    @ApiModelProperty(value = "疾病名称", name = "name", dataType = "String")
    private String name;

    /**
     * 部位名称
     */
    @ApiModelProperty(value = "部位名称", name = "placeName", dataType = "String")
    private String placeName;

    /**
     * 是否选中
     */
    @ApiModelProperty(value = "是否选中", name = "choose", dataType = "Boolean")
    private Boolean choose;

}
