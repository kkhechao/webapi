package com.zqkh.webapi.context.dto.wemedia.article;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章详情DTO
 *
 * @author 东来
 * @create 2018/4/6 0006
 */
@ApiModel(description = "文章详情DTO")
@Getter
@NoArgsConstructor
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleInfoDto implements Serializable {


    /**
     * 作者
     */
    @ApiModelProperty(value = "文章作者", name = "author", required = true, dataType = "AuthorDto")
    private AuthorDto author;

    /**
     * 文章编号
     */
    @ApiModelProperty(value = "文章编号", name = "id", required = true)
    private String id;


    /**
     * 多媒体类型
     */
    @ApiModelProperty(value = "多媒体类型", name = "mediaType", required = true)
    private MediaTypeEnumDto mediaType;


    /**
     * 封面图链接地址
     */
    @ApiModelProperty(value = "封面图链接地址", name = "iconUrl", required = true)
    private String iconUrl;

    /**
     * 标题
     */
    @ApiModelProperty(value = "文章标题", name = "title", required = true)
    private String title;

    /**
     * 关键字
     */
    @ApiModelProperty(value = "关键字", name = "keyword", required = true)
    private String keyword;

    /**
     * 详细内容
     */
    @ApiModelProperty(value = "详细内容", name = "content", required = true)
    private String content;

    /**
     * 多媒体链接地址
     */
    @ApiModelProperty(value = "多媒体资源链接地址", name = "mediaUrl")
    private String mediaUrl;

    /**
     * 简介
     */
    @ApiModelProperty(value = "文章简介", name = "synopsis")
    private String synopsis;

    /**
     * 阅读量
     */
    @ApiModelProperty(value = "阅读量", name = "viewCount")
    private int viewCount;

    /**
     * 评论数量
     */
    @ApiModelProperty(value = "评论数量", name = "commentCount")
    private long commentCount;

    /**
     * 专辑编号
     */
    @ApiModelProperty(value = "专辑编号", name = "albumId")
    private String albumId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;

}
