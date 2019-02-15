package com.zqkh.webapi.context.dto.healthy.test.paper;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 试卷答题结果DTO
 *
 * @author 东来
 * @create 2018/5/10 0010
 */
@NoArgsConstructor
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class TestResultDto implements Serializable {

    /**
     * 试卷编号
     */
    @ApiModelProperty(value = "试卷编号", name = "id", dataType = "String", required = true)
    private String id;

    /**
     * 试卷标题
     */
    @ApiModelProperty(value = "试卷标题", name = "title", dataType = "String", required = true)
    private String title;

    /**
     * 答题结果编号
     */
    @ApiModelProperty(value = "答题结果编号", name = "id", dataType = "String", required = true)
    private String testResultId;

    /**
     * 指标测试结果
     */
    @ApiModelProperty(value = "指标测试结果", name = "point", dataType = "TestResultPointDto", required = true)
    private List<TestResultPointDto> point;

}
