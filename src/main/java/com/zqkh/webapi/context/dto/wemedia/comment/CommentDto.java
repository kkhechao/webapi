package com.zqkh.webapi.context.dto.wemedia.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 评论内容DTO
 *
 * @author 悭梵
 * @date Created in 2018-05-30 9:41
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@ApiModel(description = "评论详情")
public class CommentDto implements Serializable {
    @ApiModelProperty(value = "评论标识", name = "id", required = true, dataType = "String")
    private String id;
    /**
     * 评论用户,db_uesr.t_people.id
     */
    @ApiModelProperty(value = "评论用户", name = "peopleId", required = true, dataType = "String")
    private String peopleId;
    /**
     * 冗余头像链接地址
     */
    @ApiModelProperty(value = "评论用户冗余头像链接地址", name = "headUrl", required = true, dataType = "String")
    private String headUrl;
    /**
     * 冗余昵称
     */
    @ApiModelProperty(value = "评论用户冗余昵称", name = "nickName", required = true, dataType = "String")
    private String nickName;
    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容", name = "context", required = true, dataType = "String")
    private String context;
    /**
     * 是否已点赞
     */
    @ApiModelProperty(value = "是否已点赞", name = "applaud", required = true, dataType = "boolean")
    private boolean applaud = false;
    /**
     * 点赞数
     */
    @ApiModelProperty(value = "点赞数", name = "applaudNum", required = true, dataType = "int")
    private int applaudNum;
    /**
     * 审核状态,0未审核,1审核
     */
    @ApiModelProperty(value = "审核状态", name = "status", required = true, dataType = "int")
    private int status;
    /**
     * 评论时间
     */
    @ApiModelProperty(value = "评论时间", name = "createTime", required = true, dataType = "long")
    private Date createTime;
    /**
     * 回复数量
     */
    @ApiModelProperty(value = "回复数量", name = "replyCount", required = true, dataType = "long")
    private long replyCount;
    /**
     * 回复列表
     */
    private List<CommentReplyDto> replyList;
}
