package com.zqkh.webapi.context.dto.archive.gene;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 基因档案DTO
 *
 * @author 东来
 * @create 2018/3/2 0002
 */
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "c端基因报告详情")
public class GeneArchiveInfoDto implements Serializable {

    /**
     * 套餐名
     */
    @ApiModelProperty(value = "套餐名", name = "packageName", required = true)
    private String packageName;

    /**
     * 样本
     */
    @ApiModelProperty(value = "样本", name = "sample", required = true)
    private SampleInfoDto sample;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", name = "nickName", required = true)
    private String nickName;

    /**
     * 头像链接地址
     */
    @ApiModelProperty(value = "头像链接地址", name = "headUrl")
    private String headUrl;

    /**
     * 建议
     */
    @ApiModelProperty(value = "建议", name = "suggest", required = true)
    private String suggest;


    /**
     * 疾病
     */
    @ApiModelProperty(value = "疾病", name = "disease", required = true)
    private List<DiseaseInfoDto> disease;

}
