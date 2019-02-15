package com.zqkh.webapi.context.domain.vo.healthhistory.disease;

import com.zqkh.webapi.context.domain.vo.healthhistory.drug.ApiDrugItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel("用药史信息")
@Data
public class ApiMedicationHistoryVo implements Serializable {

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
    @ApiModelProperty(value = "疗程开始时间", name = "startTime", dataType = "Date", required = true)
    private Date startTime;

    /**
     * 疗程结束时间
     */
    @ApiModelProperty(value = "疗程结束时间", name = "endTime", dataType = "Date", required = true)
    private Date endTime;

    /**
     * 药品信息
     */
    @ApiModelProperty(value = "药品信息", name = "drugs", dataType = "String", required = true)
    private List<ApiDrugItem> drugs;

    /**
     * 疗效
     */
    @ApiModelProperty(value = "疗效", name = "effect", dataType = "Effect", required = true)
    private Effect effect;
}
