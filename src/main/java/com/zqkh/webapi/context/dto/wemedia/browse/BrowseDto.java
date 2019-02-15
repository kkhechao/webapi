package com.zqkh.webapi.context.dto.wemedia.browse;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 浏览历史WebAPI返回对象
 *
 * @author 悭梵
 * @date Created in 2018-05-08 10:37
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@ApiModel(description = "浏览历史DTO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrowseDto implements Serializable {
    @ApiModelProperty(value = "浏览记录标识", name = "id", required = true, dataType = "string")
    private String id;
    @ApiModelProperty(value = "浏览对象类型", name = "browseTypeDto", required = true, dataType = "BrowseTypeDto")
    private BrowseTypeDto browseTypeDto;
    @ApiModelProperty(value = "浏览用户标识", name = "userId", required = true, dataType = "string")
    private String userId;
    @ApiModelProperty(value = "浏览对象标识", name = "objectId", required = true, dataType = "string")
    private String objectId;
    @ApiModelProperty(value = "首次浏览时间", name = "createTime", required = true, dataType = "long")
    private Date createTime;
    @ApiModelProperty(value = "浏览次数", name = "num", required = true, dataType = "long")
    private int num = 1;
    @ApiModelProperty(value = "最近浏览时间", name = "modifyTime", required = true, dataType = "long")
    private Date modifyTime;
}
