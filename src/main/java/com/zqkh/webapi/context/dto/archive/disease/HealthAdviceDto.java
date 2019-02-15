package com.zqkh.webapi.context.dto.archive.disease;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 预防保健建议
 *
 * @author 东来
 * @create 2018/6/28 0028
 */
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@ApiModel("预防保健建议")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HealthAdviceDto implements Serializable {

    /**
     * 建议标题
     */
    @ApiModelProperty(value = "建议标题", name = "title")
    private String title;

    /**
     * 建议图标资源编号
     */
    @ApiModelProperty(value = "图片资源编号", name = "icon")
    private String icon;

    /**
     * 图片链接地址
     */
    @ApiModelProperty(value = "图片链接地址", name = "iconUrl")
    private String iconUrl;

    /**
     * 建议内容
     */
    @ApiModelProperty(value = "建议内容", name = "content")
    private String content;


}
