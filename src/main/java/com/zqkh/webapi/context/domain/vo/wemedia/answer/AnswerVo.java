package com.zqkh.webapi.context.domain.vo.wemedia.answer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 回答问题表单对象
 *
 * @author 悭梵
 * @date Created in 2018-06-21 17:42
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@ApiModel(description = "回答问题")
public class AnswerVo implements Serializable {
    /**
     * 问题标识
     */
    @ApiModelProperty(value = "问题标识", name = "questionId", required = true, dataType = "String")
    private String questionId;
    /**
     * 内容
     */
    @ApiModelProperty(value = "回答内容", name = "content", required = true, dataType = "String")
    private String content;
}
