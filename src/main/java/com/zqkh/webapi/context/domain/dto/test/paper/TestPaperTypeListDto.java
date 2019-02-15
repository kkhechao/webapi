package com.zqkh.webapi.context.domain.dto.test.paper;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 测题类型列表DTO
 *
 * @author 东来
 * @create 2018/6/7 0007
 */
@NoArgsConstructor
@Getter
@Setter
@ApiModel(description = "测题类型")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestPaperTypeListDto implements Serializable {

    /**
     * 类型编号
     */
    @ApiModelProperty(value = "类型编号", name = "id", dataType = "String", required = true)
    private String id;

    /**
     * 类型名称
     */
    @ApiModelProperty(value = "类型名称", name = "name", dataType = "String", required = true)
    private String name;

    /**
     * 类型描述/副标题
     */
    @ApiModelProperty(value = "类型描述/副标题", name = "desc", dataType = "String", required = true)
    private String desc;


    /**
     * 前端显示模板名称
     */
    @ApiModelProperty(value = "template", name = "templateName", dataType = "String")
    private String template;


    /**
     * 测题
     */
    @ApiModelProperty(value = "测题", name = "testPaper", dataType = "TestPaperDto", required = true)
    private List<TestPaperDto> testPaper;


}
