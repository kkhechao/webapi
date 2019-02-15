package com.zqkh.webapi.context.dto.wemedia.type;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 分类Dto
 *
 * @author: 悭梵
 * @date: Created in 2018/4/6 11:03
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "分类")
public class TypeSimpleDto implements Serializable {

    @ApiModelProperty(value = "分类编号", name = "id", required = true, dataType = "String")
    private String id;

    @ApiModelProperty(value = "分类名称", name = "name", required = true, dataType = "String")
    private String name;

    @ApiModelProperty(value = "分类英文名称", name = "ename", dataType = "String")
    private String  ename;
}
