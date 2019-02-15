package com.zqkh.webapi.web.v1.wemedia;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.domain.vo.wemedia.comment.CommentVo;
import com.zqkh.webapi.context.dto.wemedia.column.CommentArticleDto;
import com.zqkh.webapi.context.dto.wemedia.column.CommentReplyDto;
import com.zqkh.webapi.context.dto.wemedia.comment.CommentDto;
import com.zqkh.webapi.context.service.CommentService;
import com.zqkh.webapi.context.utils.CusAccessObjectUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 评论WEB API Controller
 *
 * @author 悭梵
 * @date Created in 2018-05-30 14:12
 */
@RestController
@RequestMapping("/me/media/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 评论对象
     *
     * @param commentVo
     */
    @PostMapping("")
    @ApiOperation(value = "api_comment_add", notes = "评论对象")
    public void comment(@RequestBody CommentVo commentVo, HttpServletRequest request) {
        commentService.comment(commentVo.getObjectId(), commentVo.getType().name(), commentVo.getContent(), CusAccessObjectUtil.getClientIpAddress(request));
    }

    /**
     * 评论文章
     *
     * @param commentArticleDto
     */
    @PostMapping("/article")
    @ApiOperation(value = "api_comment_article", notes = "评论文章")
    public void commentArticle(@RequestBody CommentArticleDto commentArticleDto, HttpServletRequest request) {
        commentService.comment(commentArticleDto.getArticleId(), CommentVo.Type.ARTICLE.name(), commentArticleDto.getContent(), CusAccessObjectUtil.getClientIpAddress(request));
    }

    /**
     * 评论回复
     *
     * @param commentReplyDto
     */
    @PostMapping("/reply")
    @ApiOperation(value = "api_comment_reply", notes = "评论回复")
    public void commentReply(@RequestBody CommentReplyDto commentReplyDto, HttpServletRequest request) {
        commentService.commentReply(commentReplyDto.getCommentId(), commentReplyDto.getContent(), CusAccessObjectUtil.getClientIpAddress(request));
    }

    /**
     * 评论点赞, 再次调用取消点赞
     *
     * @param commentId
     */
    @PostMapping("/applaud")
    @ApiOperation(value = "api_comment_applaud", notes = "评论点赞，再次调用取消点赞")
    @ApiImplicitParam(name = "commentId", value = "评论标识", paramType = "path")
    public void applaud(@RequestBody String commentId) {
        commentService.applaud(commentId);
    }

    /**
     * 分页查询文章评论信息
     *
     * @param articleId 评论文章标识
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Anonymous
    @GetMapping("/article")
    @ApiOperation(value = "api_comment_article", notes = "分页查询文章评论信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章标识", paramType = "query"),
            @ApiImplicitParam(name = "第几页", value = "pageIndex", required = false, defaultValue = "1", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "每页显示", value = "pageSize", required = false, defaultValue = "20", paramType = "query", dataType = "String")
    })
    public PageResult<CommentDto> list(@RequestParam(name = "articleId") String articleId, @RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex, @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize) {
        return commentService.list(articleId, pageIndex, pageSize);
    }


    /**
     * 分页查询文章评论信息
     *
     * @param objectId 评论对象标识
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Anonymous
    @GetMapping("/list")
    @ApiOperation(value = "api_comment_article", notes = "分页查询文章评论信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "objectId", value = "评论对象标识", paramType = "query"),
            @ApiImplicitParam(name = "objectType", value = "评论类型，ARTICLE：文章，ANSWER：回答", paramType = "query"),
            @ApiImplicitParam(name = "第几页", value = "pageIndex", required = false, defaultValue = "1", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "每页显示", value = "pageSize", required = false, defaultValue = "20", paramType = "query", dataType = "String")
    })
    public PageResult<CommentDto> commentObjectList(@RequestParam(name = "objectId") String objectId, @RequestParam(name = "objectType") String objectType, @RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex, @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize) {
        return commentService.commentObjectList(objectId, objectType, pageIndex, pageSize);
    }


}
