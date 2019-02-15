package com.zqkh.webapi.context.dto.healthy.program;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 查看解决方案DTO
 *
 * @author 东来
 * @create 2018/5/15 0015
 */
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("查看解决方案")
public class SelectProgramActionDto implements Serializable {

    @ApiModelProperty(value = "答题结果编号", name = "id", dataType = "String", required = true)
    private String id;

    @ApiModelProperty(value = "指标名称", name = "pointName", dataType = "String", required = true)
    private String pointName;

    @ApiModelProperty(value = "试卷标题", name = "title", dataType = "String", required = true)
    private String title;

    @ApiModelProperty(value = "图片链接", name = "coverUrl", dataType = "String", required = true)
    private String coverUrl;

    @ApiModelProperty(value = "方案内容", name = "actionContent", dataType = "String", required = true)
    private String actionContent;

    @ApiModelProperty(value = "结果", name = "result", dataType = "String", required = true)
    private String result;

}
