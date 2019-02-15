package com.zqkh.webapi.context.domain.vo.test.paper;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 试卷答案
 *
 * @author 东来
 * @create 2018/5/14 0014
 */
@ApiModel("答案")
@Getter
@Setter
@NoArgsConstructor
public class AnswerVo implements Serializable {

    /**
     * 试题编号
     */
    @ApiModelProperty(value = "试卷编号", name = "index", dataType = "int", required = true)
    private Integer index;

    /**
     * 答案
     */
    @ApiModelProperty(value = "用户答案", name = "answer", dataType = "String", required = true)
    private String answer;

    /**
     * 上一题
     */
    @ApiModelProperty(value = "上一题", name = "previousIndex", dataType = "int", required = true)
    private Integer previousIndex;
}
