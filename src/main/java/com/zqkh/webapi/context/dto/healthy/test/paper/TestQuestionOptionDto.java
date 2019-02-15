package com.zqkh.webapi.context.dto.healthy.test.paper;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 试题选项
 *
 * @author 东来
 * @create 2018/5/10 0010
 */
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("试题选项")
public class TestQuestionOptionDto implements Serializable {

    @ApiModelProperty(value = "选项名", name = "name", dataType = "String", required = true)
    private String name;

    @ApiModelProperty(value = "选项值", name = "value", dataType = "String", required = true)
    private String value;

    @ApiModelProperty(value = "下一题起始值", name = "startIndex", dataType = "int")
    private Integer startIndex;

    @ApiModelProperty(value = "下一题终止值", name = "endIndex", dataType = "int")
    private Integer endIndex;


}
