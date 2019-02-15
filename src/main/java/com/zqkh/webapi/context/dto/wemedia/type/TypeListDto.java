package com.zqkh.webapi.context.dto.wemedia.type;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Type List DTO
 *
 * @author 悭梵
 * @date Created in 2018-04-06 17:12
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "分类")
public class TypeListDto {

    @ApiModelProperty(value = "分类编号", name = "id", required = true, dataType = "String")
    private String id;

    @ApiModelProperty(value = "分类名称", name = "name", required = true, dataType = "String")
    private String name;

    @ApiModelProperty(value = "分类英文名称", name = "ename", dataType = "String")
    private String  ename;

    @ApiModelProperty(value = "分类图片URL", name = "iconUrl", required = true, dataType = "String")
    private String iconUrl;

    @ApiModelProperty(value = "文章数量", name = "articleCount", required = true, dataType = "int")
    private long articleCount;

    @ApiModelProperty(value = "简介", name = "synopsis", dataType = "String")
    private String synopsis;
}
