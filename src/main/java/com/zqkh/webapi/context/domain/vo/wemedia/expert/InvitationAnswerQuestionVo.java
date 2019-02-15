package com.zqkh.webapi.context.domain.vo.wemedia.expert;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 邀请专家回答问题Vo对象
 *
 * @author 悭梵
 * @date Created in 2018-06-29 16:13
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@ApiModel(description = "邀请专家回答问题")
public class InvitationAnswerQuestionVo implements Serializable {

    /**
     * 专家标识，多个用英文逗号分隔
     */
    @ApiModelProperty(value = "被邀请的专家标识列表", name = "expertId", required = true, dataType = "String")
    private String expertId;
    /**
     * 问题标识
     */
    @ApiModelProperty(value = "问题标识", name = "questionId", required = true, dataType = "String")
    private String questionId;

}
