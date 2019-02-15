package com.zqkh.webapi.context.dto.archive.disease;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 检测概述
 *
 * @author 东来
 * @create 2018/6/26 0026
 */
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@ApiModel("检测概述")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetectionOfOverviewDto implements Serializable {

    /**
     * 中国发病率分子
     */
    @ApiModelProperty(value = "中国发病率分子", name = "chinaMorbidityMolecule", dataType = "int")
    private Integer chinaMorbidityMolecule;

    /**
     * 中国发病率分母
     */
    @ApiModelProperty(value = "中国发病率分母", name = "chinaMorbidityDenominator", dataType = "int")
    private Integer chinaMorbidityDenominator;

    /**
     * 男性发病时间
     */
    @ApiModelProperty(value = "男性发病时间", name = "man", dataType = "MorbidityAgeGroupDto")
    private MorbidityAgeGroupDto man;

    /**
     * 女性发病时间
     */
    @ApiModelProperty(value = "女性发病时间", name = "woman", dataType = "MorbidityAgeGroupDto")
    private MorbidityAgeGroupDto woman;


    /**
     * 发病地域占比
     */
    @ApiModelProperty(value = "发病地域占比", name = "epidemicArea", dataType = "EpidemicAreaDto")
    private List<EpidemicAreaDto> epidemicArea;


}
