package com.zqkh.webapi.context.dto.healthy.test.paper;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 试题DTO
 *
 * @author 东来
 * @create 2018/5/10 0010
 */
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("试题详情")
public class TestQuestionListDto implements Serializable {

    /**
     * 第几题
     */
    @ApiModelProperty(value = "第几题", name = "index", dataType = "int", required = true)
    private Integer index;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", name = "title", dataType = "int", required = true)
    private String title;

    /**
     * 是否必填
     */
    private Boolean required = false;

    /**
     * 选项
     */
    @ApiModelProperty(value = "选项", name = "option", dataType = "TestQuestionOptionDto", required = true)
    private List<TestQuestionOptionDto> option;


}
