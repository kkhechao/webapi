package com.zqkh.webapi.context.domain.dto.program;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 方案结果详情Dto
 *
 * @author 东来
 * @create 2018/6/8 0008
 */
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "方案结果")
public class ProgramResultInfoDto implements Serializable {

    /**
     * 方案结果编号
     */
    @ApiModelProperty(value = "方案结果编号", name = "id", dataType = "String", required = true)
    private String id;

    /**
     * 起始时间
     */
    @ApiModelProperty(value = "起始时间", name = "startTime", dataType = "Date", required = true)
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", name = "endTime", dataType = "Date", required = true)
    private Date endTime;

    /**
     * 持续天数
     */
    @ApiModelProperty(value = "持续天数", name = "insistDay", dataType = "long", required = true)
    private long insistDay;

    /**
     * 徘徊次数
     */
    @ApiModelProperty(value = "徘徊次数", name = "wanderNum", dataType = "long", required = true)
    private int wanderNum;

    /**
     * 放弃次数
     */
    @ApiModelProperty(value = "放弃次数", name = "giveUpNum", dataType = "long", required = true)
    private int giveUpNum;

    /**
     * 完成任务次数
     */
    @ApiModelProperty(value = "完成任务次数", name = "finishNum", dataType = "long", required = true)
    private long finishNum;

    /**
     * 总人数
     */
    @ApiModelProperty(value = "总人数", name = "total", dataType = "long", required = true)
    private long total = 0;

    /**
     * 超越人数
     */
    @ApiModelProperty(value = "超越人数", name = "transcend", dataType = "long", required = true)
    private long transcend = 0;

    /**
     * 试题编号
     */
    @ApiModelProperty(value = "试题编号", name = "testPaperId", dataType = "String")
    private String testPaperId;
    /**
     * 试题标题
     */
    @ApiModelProperty(value = "试题标题", name = "testPaperTitle", dataType = "String")
    private String testPaperTitle;
}
