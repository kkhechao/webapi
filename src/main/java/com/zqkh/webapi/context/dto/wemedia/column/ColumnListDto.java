package com.zqkh.webapi.context.dto.wemedia.column;

import com.zqkh.webapi.context.dto.wemedia.type.TypeSimpleDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 专栏下拉列表DTO
 *
 * @author 东来
 * @create 2018/4/6 0006
 */
@Getter
@Setter
@NoArgsConstructor
public class ColumnListDto implements Serializable {
    /**
     * 专栏标识
     */
    @ApiModelProperty(value = "专栏标识", name = "id", required = true, dataType = "String")
    private String id;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime", required = true, dataType = "long")
    private Date createTime;
    /**
     * 专栏名称
     */
    @ApiModelProperty(value = "专栏名称", name = "name", required = true, dataType = "String")
    private String name;
    /**
     * 专栏介绍
     */
    @ApiModelProperty(value = "专栏介绍", name = "introduce", required = true, dataType = "String")
    private String introduce;
    /**
     * 专栏图片
     */
    @ApiModelProperty(value = "专栏图片URL地址", name = "iconResourceUrl", required = true, dataType = "String")
    private String iconResourceUrl;
    /**
     * 关注数量
     */
    @ApiModelProperty(value = "创建时间", name = "followNum", required = true, dataType = "long")
    private long followNum = 0L;
    /**
     * 是否关注
     */
    @ApiModelProperty(value = "是否关注", name = "follow", required = true, dataType = "long")
    private boolean follow = false;

    /**
     * 状态，REMOVE:删除,TO_BE_AUDITED:待审核,NORMAL:正常,LOCK:锁定
     */
    @ApiModelProperty(value = "状态，REMOVE:删除,TO_BE_AUDITED:待审核,NORMAL:正常,LOCK:锁定", name = "status", required = true, dataType = "String")
    private String status;

    /**
     * 状态时间
     */
    @ApiModelProperty(value = "状态时间", name = "statusTime", required = true, dataType = "long")
    private Date statusTime;

    /**
     * 副标题
     */
    @ApiModelProperty(value = "副标题", name = "subhead", required = true, dataType = "String")
    private String subhead;

    /**
     * 类型
     */
    private List<TypeSimpleDto> typeList;

}
