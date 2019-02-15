package com.zqkh.webapi.context.dto.wemedia.column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 专栏下拉列表DTO
 *
 * @author 东来
 * @create 2018/4/6 0006
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "专栏")
public class ColumnSimpleDto implements Serializable {
    @ApiModelProperty(value = "专栏标识", name = "id", required = true,dataType = "String")
    private String id;
    @ApiModelProperty(value = "专栏名称", name = "name", required = true,dataType = "String")
    private String name;
}
