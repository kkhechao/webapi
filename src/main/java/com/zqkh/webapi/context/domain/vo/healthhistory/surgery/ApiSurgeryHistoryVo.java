package com.zqkh.webapi.context.domain.vo.healthhistory.surgery;

import com.zqkh.webapi.context.domain.enums.ApiDiseaseType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel("手术史信息")
@Data
public class ApiSurgeryHistoryVo implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "id", dataType = "String", required = false)
    private String id;

    /**
     * 家庭成员ID
     */
    @ApiModelProperty(value = "家庭成员ID", name = "familyMemberId", dataType = "String", required = true)
    private String familyMemberId;


    /**
     * 手术时间
     */
    @ApiModelProperty(value = "手术时间", name = "surgeryTime", dataType = "Date", required = true)
    private Date surgeryTime;


    /**
     * 是否痊愈
     */
    @ApiModelProperty(value = "是否痊愈", name = "cured", dataType = "Boolean", required = true)
    private Boolean cured;


    /**
     * 疾病备注
     */
    @ApiModelProperty(value = "疾病备注", name = "remark", dataType = "String", required = false)
    private String remark;


    /**
     * 手术原因
     */
    @ApiModelProperty(value = "手术原因", name = "disease", dataType = "String", required = true)
    private String disease;

    /**
     * 疾病类型
     */
    @ApiModelProperty(value = "疾病类型", name = "diseaseType", dataType = "String", required = true)
    private ApiDiseaseType diseaseType;

}
