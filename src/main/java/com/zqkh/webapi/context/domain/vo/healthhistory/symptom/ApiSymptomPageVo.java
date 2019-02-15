package com.zqkh.webapi.context.domain.vo.healthhistory.symptom;

import com.zqkh.archive.feign.enums.SymptomPlaceType;
import com.zqkh.webapi.context.domain.vo.healthhistory.PageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel("根据部位名称 分页获取疾病信息列表")
@EqualsAndHashCode(callSuper = true)
public class ApiSymptomPageVo extends PageVo {


    @ApiModelProperty(value = "部位名称", name = "placeName", required = true, dataType = "SymptomPlaceType")
    private SymptomPlaceType placeName;

    @ApiModelProperty(value = "已选择的疾病id，如果部位为 Other, 则为用户自定义疾病名称", name = "symptomId", required = false, dataType = "String")
    private String symptomId;

    @ApiModelProperty(value = "已选择的疾病部位类型，如果部位为 Other, 则为 Other", name = "diseaseType", required = false, dataType = "SymptomPlaceType")
    private SymptomPlaceType diseaseType;

}
