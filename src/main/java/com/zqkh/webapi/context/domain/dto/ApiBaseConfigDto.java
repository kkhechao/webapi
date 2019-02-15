package com.zqkh.webapi.context.domain.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@ApiModel(description = "配置信息Dto")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiBaseConfigDto implements Serializable {


    @ApiModelProperty(value = "Key", name = "key", dataType = "String")
    private String key;

    @ApiModelProperty(value = "Value", name = "value", dataType = "String")
    private String value;

    @ApiModelProperty(value = "排序值", name = "sort", dataType = "Integer")
    private Integer sort;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public String setValue(String value) {
        String oldValue = this.value;

        this.value = value;

        return oldValue;
    }

}
