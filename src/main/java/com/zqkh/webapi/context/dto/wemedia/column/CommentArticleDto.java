package com.zqkh.webapi.context.dto.wemedia.column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 评论文章
 *
 * @author 悭梵
 * @date Created in 2018-06-07 11:26
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@ApiModel(description = "评论文章")
public class CommentArticleDto implements Serializable {
    @ApiModelProperty(value = "文章标识", name = "articleId", required = true, dataType = "String")
    private String articleId;
    @ApiModelProperty(value = "评论内容", name = "content", required = true, dataType = "String")
    private String content;
}
