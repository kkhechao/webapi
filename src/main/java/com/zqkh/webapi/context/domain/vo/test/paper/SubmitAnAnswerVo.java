package com.zqkh.webapi.context.domain.vo.test.paper;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 提交试卷答案
 *
 * @author 东来
 * @create 2018/5/14 0014
 */
@ApiModel("提交试卷答案")
@NoArgsConstructor
@Getter
@Setter
public class SubmitAnAnswerVo implements Serializable {

    /**
     * 试卷编号
     */
    @ApiModelProperty(value = "试卷编号", name = "id", dataType = "String", required = true)
    private String id;


    /**
     * 家庭成员编号
     */
    @ApiModelProperty(value = "家庭成员编号", name = "familyMemberId", dataType = "String", required = true)
    private String familyMemberId;

    /**
     * 提交的答案
     */
    @ApiModelProperty(value = "答案", name = "answer", dataType = "AnswerVo", required = true)
    private List<AnswerVo> answer;


}
