package com.zqkh.webapi.context.dto.wemedia.album;

import com.zqkh.webapi.context.dto.wemedia.article.MediaTypeEnumDto;
import com.zqkh.webapi.context.dto.wemedia.column.ColumnSimpleDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 东来
 * @create 2018/4/6 0006
 */
@NoArgsConstructor
@Getter
@Setter
@ApiModel(description = "专栏详情DTO")
public class AlbumInfoDto implements Serializable {

    @ApiModelProperty(value = "专辑编号", name = "id", required = true, dataType = "String")
    private String id;
    @ApiModelProperty(value = "专辑名称", name = "name", required = true, dataType = "String")
    private String name;
    @ApiModelProperty(value = "关键字", name = "keyword", required = true, dataType = "String")
    private String keyword;
    @ApiModelProperty(value = "文章数量", name = "count", required = true, dataType = "long")
    private long count = 0;
    @ApiModelProperty(value = "创建人", name = "createUserId", required = true, dataType = "String")
    private String createUserId;
    @ApiModelProperty(value = "创建时间", name = "createUserId", required = true, dataType = "long")
    private Date createTime;
    @ApiModelProperty(value = "简介", name = "synopsis", dataType = "String")
    private String synopsis;
    @ApiModelProperty(value = "专辑图标链接地址", name = "iconUrl", dataType = "String")
    private String iconUrl;
    @ApiModelProperty(value = "专辑类型", name = "mediaType", dataType = "MediaTypeEnumDto")
    private MediaTypeEnumDto mediaType;
    @ApiModelProperty(value = "是否订阅", name = "subscribe", dataType = "boolean")
    private boolean subscribe = false;
    @ApiModelProperty(value = "订阅数量", name = "subscribeNum", dataType = "long")
    private long subscribeNum ;
    /**
     * 专栏信息
     */
    private ColumnSimpleDto column;
}
