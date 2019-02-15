package com.zqkh.webapi.context.domain.vo.wemedia.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 评论
 *
 * @author 悭梵
 * @date Created in 2018-06-22 11:22
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@ApiModel(description = "评论")
public class CommentVo implements Serializable {
    @ApiModelProperty(value = "评论对象标识", name = "objectId", required = true, dataType = "String")
    private String objectId;
    @ApiModelProperty(value = "评论对象类型", name = "type", required = true)
    private Type type;
    @ApiModelProperty(value = "评论内容", name = "content", required = true, dataType = "String")
    private String content;

    public enum Type {
        /**
         * 文章
         */
        ARTICLE,
        /**
         * 回复
         */
        COMMENT,
        /**
         * 回答
         */
        ANSWER;
    }
}
