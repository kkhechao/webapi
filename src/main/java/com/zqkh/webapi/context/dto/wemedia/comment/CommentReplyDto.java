package com.zqkh.webapi.context.dto.wemedia.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 评论回复DTO
 *
 * @author 悭梵
 * @date Created in 2018-05-30 9:41
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@ApiModel(description = "评论被回复人信息")
public class CommentReplyDto extends CommentDto {
    /**
     * 被回复人标识
     */
    @ApiModelProperty(value = "被回复人标识", name = "replyPeopleId", required = true, dataType = "String")
    private String replyPeopleId;
    /**
     * 被回复人冗余头像链接地址
     */
    @ApiModelProperty(value = "被回复人冗余头像链接地址", name = "replyHeadUrl", required = true, dataType = "String")
    private String replyHeadUrl;
    /**
     * 被回复人冗余昵称
     */
    @ApiModelProperty(value = "被回复人冗余昵称", name = "replyNickName", required = true, dataType = "String")
    private String replyNickName;
}
