package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.dto.wemedia.comment.CommentDto;

/**
 * 评论WEB API 业务层接口定义
 *
 * @author 悭梵
 * @date Created in 2018-05-30 14:23
 */
public interface CommentService {

    /**
     * 评论
     *
     * @param objectId
     * @param context
     */
    void comment(String objectId, String type, String context, String ip);

    /**
     * 评论回复
     *
     * @param commentId
     * @param context
     */
    void commentReply(String commentId, String context, String ip);

    /**
     * 评论点赞
     *
     * @param commentId
     */
    void applaud(String commentId);

    /**
     * 分页查询评论信息
     *
     * @param articleId 评论文章标识
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageResult<CommentDto> list(String articleId, int pageIndex, int pageSize);
    /**
     * 分页查询评论信息
     *
     * @param objectId 评论对象标识
     * @param objectType 评论类型，ARTICLE：文章，ANSWER：回答
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageResult<CommentDto> commentObjectList( String objectId,  String objectType, int pageIndex, int pageSize);
}
