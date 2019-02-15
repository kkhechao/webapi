package com.zqkh.webapi.context.dto.archive.gene;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 检测位点DTO
 *
 * @author 东来
 * @create 2018/1/31 0031
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "疾病位点详情")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocusInfoDto {

    /**
     * 位点自定义编号
     */
    @ApiModelProperty(value = "自定义位点编号", name = "locusId", required = true)
    private String locusId;

    /**
     * 基因
     */
    @ApiModelProperty(value = "基因", name = "gene", required = true)
    private String gene;

    /**
     * 染色体定位
     */
    @ApiModelProperty(value = "染色体定位", name = "chromosomePosition", required = true)
    private String chromosomePosition;

    /**
     * 命中的基因型
     */
    @ApiModelProperty(value = "命中的基因型", name = "geneType", required = true)
    private String geneType;

    /**
     * 存在的基因型
     */
    @ApiModelProperty(value = "命中的基因型", name = "allGenType", required = true)
    private List<String> allGenType;


    /**
     * 位点图片链接地址
     */
    @ApiModelProperty(value = "位点图片链接地址", name = "locusImageUrl", required = true)
    private String locusImageUrl;

    /**
     * 位点解释
     */
    @ApiModelProperty(value = "位点解释", name = "siteInstruction", required = true)
    private String siteInstruction;
}
