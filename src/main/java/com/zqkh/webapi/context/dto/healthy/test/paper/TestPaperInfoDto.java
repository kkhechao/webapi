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
 * 试卷详情DTO
 *
 * @author 东来
 * @create 2018/5/10 0010
 */
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("试卷详情")
public class TestPaperInfoDto implements Serializable {

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
     * 试卷备注
     */
    @ApiModelProperty(value = "试卷备注", name = "remark", dataType = "String", required = true)
    private String remark;

    /**
     * 试题
     */
    @ApiModelProperty(value = "试题", name = "question", dataType = "TestQuestionListDto", required = true)
    private List<TestQuestionListDto> question;

    /**
     * 用户答题结果
     */
    @ApiModelProperty(value = "用户答题结果", name = "testResult", dataType = "FamilyMemberTestResultDto")
    private List<FamilyMemberTestResultDto> testResult;

    /**
     * 试题答题结果单
     */
    @ApiModelProperty(value = "试题", name = "question", dataType = "TestQuestionListDto")
    private String testResultId;

}
