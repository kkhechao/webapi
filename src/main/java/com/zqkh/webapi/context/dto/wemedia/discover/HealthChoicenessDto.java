package com.zqkh.webapi.context.dto.wemedia.discover;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zqkh.webapi.context.dto.wemedia.article.MediaTypeEnumDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 健康精选DTO
 *
 * @author 东来
 * @create 2018/4/6 0006
 */
@ApiModel(description = "文章精选")
@Getter
@NoArgsConstructor
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HealthChoicenessDto implements Serializable {

    @ApiModelProperty(value = "文章编号", name = "id", required = true,dataType = "String")
    private String id;
    @ApiModelProperty(value = "文章标题", name = "title", required = true,dataType = "String")
    private String title;
    @ApiModelProperty(value = "分类名称", name = "typeName", required = true,dataType = "String")
    private String typeName;
    @ApiModelProperty(value = "封面图链接地址", name = "iconUrl", required = true,dataType = "String")
    private String iconUrl;
    @ApiModelProperty(value = "多媒体链接地址", name = "mediaUrl",dataType = "String")
    private String mediaUrl;
    @ApiModelProperty(value = "多媒体类型", name = "mediaType", required = true,dataType = "MediaTypeEnumVo")
    private MediaTypeEnumDto mediaType;
    @ApiModelProperty(value = "浏览数", name = "viewCount", required = true,dataType = "long")
    private int viewCount;
    @ApiModelProperty(value = "创建时间", name = "createTime", required = true,dataType = "long")
    private Date createTime;
    @ApiModelProperty(value = "简介", name = "synopsis", required = true,dataType = "String")
    private String synopsis;
    @ApiModelProperty(value = "作者", name = "authorNickName", required = true,dataType = "String")
    private String authorNickName;



}
