package com.zqkh.webapi.context.domain.dto.healthhistory.disease;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zqkh.webapi.context.domain.enums.ApiMedicationType;
import com.zqkh.webapi.context.domain.vo.healthhistory.drug.ApiDrugItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel(description = "疾病用药史信息")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiMedicationHistoryDto implements Serializable {


    /**
     * 疗效
     */
    @ApiModel(description = "疗效")
    public enum Effect {
        WELL,
        ORDINARY,
        BAD
    }

    /**
     * 疗程开始时间
     */
    @ApiModelProperty(value = "疗程开始时间", name = "startTime", dataType = "Date")
    private Date startTime;

    /**
     * 疗程结束时间
     */
    @ApiModelProperty(value = "疗程结束时间", name = "endTime", dataType = "Date")
    private Date endTime;

    /**
     * 药品信息
     */
    @ApiModelProperty(value = "药品信息", name = "drugs", dataType = "Drug")
    private List<ApiDrugItem> drugs;

    /**
     * 疗效
     */
    @ApiModelProperty(value = "疗效", name = "effect", dataType = "Effect")
    private Effect effect;

    /**
     * 药品类型
     */
    @ApiModelProperty(value = "药品类型", name = "medicationType", dataType = "ApiMedicationType")
    private ApiMedicationType medicationType;
}
