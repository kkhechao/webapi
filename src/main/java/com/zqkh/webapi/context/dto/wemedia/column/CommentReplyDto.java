package com.zqkh.webapi.context.dto.wemedia.column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 评论回复
 *
 * @author 悭梵
 * @date Created in 2018-06-07 11:26
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@ApiModel(description = "评论回复")
public class CommentReplyDto implements Serializable {
    @ApiModelProperty(value = "评论标识", name = "commentId", required = true, dataType = "String")
    private String commentId;
    @ApiModelProperty(value = "回复内容", name = "content", required = true, dataType = "String")
    private String content;
}
