package com.zqkh.webapi.context.domain.dto.test.paper;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 试卷DTO
 *
 * @author 东来
 * @create 2018/5/9 0009
 */
@ApiModel("试卷")
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestPaperDto {

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
     * 是否完成
     */
    @ApiModelProperty(value = "是否完成", name = "done", dataType = "boolean", required = true)
    private Boolean done;

    /**
     * 试卷封面图
     */
    @ApiModelProperty(value = "试卷封面图", name = "coverUrl", dataType = "String", required = true)
    private String coverUrl;

    /**
     * 答题结果编号
     */
    @ApiModelProperty(value = "答题结果编号", name = "testResultId", dataType = "String")
    private String testResultId;


}
