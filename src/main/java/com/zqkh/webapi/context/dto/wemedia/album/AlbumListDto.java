package com.zqkh.webapi.context.dto.wemedia.album;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "专辑列表")
public class AlbumListDto implements Serializable {

    @ApiModelProperty(value = "专辑编号", name = "id", required = true, dataType = "String")
    private String id;
    @ApiModelProperty(value = "专辑名称", name = "name", required = true, dataType = "String")
    private String name;
    @ApiModelProperty(value = "专辑简介", name = "synopsis", required = true, dataType = "String")
    private String synopsis;
    @ApiModelProperty(value = "专辑封面图链接", name = "iconUrl", required = true, dataType = "String")
    private String iconUrl;
    @ApiModelProperty(value = "专辑多媒体类型", name = "mediaType", required = true, dataType = "String")
    private String mediaType;
    @ApiModelProperty(value = "专辑下文章数量", name = "articleCount", required = true, dataType = "long")
    private long articleCount;
    @ApiModelProperty(value = "专辑下文章阅读数量", name = "readCount", required = true, dataType = "long")
    private long readCount;
    @ApiModelProperty(value = "创建人", name = "createUserId", required = true, dataType = "String")
    private String createUserId;
    @ApiModelProperty(value = "创建时间", name = "createUserId", required = true, dataType = "long")
    private Date createTime;

}
