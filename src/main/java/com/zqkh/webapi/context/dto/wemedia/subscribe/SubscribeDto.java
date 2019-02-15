package com.zqkh.webapi.context.dto.wemedia.subscribe;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zqkh.webapi.context.dto.wemedia.article.MediaTypeEnumDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 订阅WebAPI返回对象
 *
 * @author 悭梵
 * @date Created in 2018-05-08 16:38
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@ApiModel(description = "订阅专辑DTO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscribeDto implements Serializable {
    @ApiModelProperty(value = "订阅记录标识", name = "id", required = true, dataType = "string")
    private String id;
    @ApiModelProperty(value = "订阅用户标识", name = "userId", required = true, dataType = "string")
    private String userId;
    @ApiModelProperty(value = "订阅时间", name = "createTime", required = true, dataType = "long")
    private Date createTime;
    @ApiModelProperty(value = "订阅专辑标识", name = "albumId", required = true, dataType = "string")
    private String albumId;
    @ApiModelProperty(value = "订阅专辑名称", name = "albumName", required = true, dataType = "string")
    private String albumName;
    @ApiModelProperty(value = "订阅专辑简介", name = "albumSynopsis", dataType = "string")
    private String albumSynopsis;
    @ApiModelProperty(value = "订阅专辑关键字", name = "albumKeyword", dataType = "string")
    private String albumKeyword;
    @ApiModelProperty(value = "订阅专辑封面图片URL", name = "albumIconResourceUrl", dataType = "string")
    private String albumIconResourceUrl;
    @ApiModelProperty(value = "订阅专辑多媒体类型", name = "albumMediaType", dataType = "string")
    private MediaTypeEnumDto albumMediaType;
    @ApiModelProperty(value = "订阅专辑创建人", name = "albumAuthor", dataType = "string")
    private String albumAuthor;
    @ApiModelProperty(value = "订阅专辑创建时间", name = "albumCreateTime", dataType = "long")
    private Date albumCreateTime;
    @ApiModelProperty(value = "订阅专辑更新时间", name = "albumModifyTime", dataType = "long")
    private Date albumModifyTime;
    @ApiModelProperty(value = "订阅专辑文章数量", name = "albumArticleCount", dataType = "long")
    private long albumArticleCount;
    @ApiModelProperty(value = "订阅专辑阅读数量", name = "albumViewCount", dataType = "long")
    private long albumViewCount;
    @ApiModelProperty(value = "上次浏览后更新数量", name = "lastBrowseAfterArticleUpdateCount", dataType = "long")
    private long lastBrowseAfterArticleUpdateCount;
}
