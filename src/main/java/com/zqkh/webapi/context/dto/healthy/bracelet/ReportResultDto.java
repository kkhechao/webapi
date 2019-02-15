package com.zqkh.webapi.context.dto.healthy.bracelet;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 报表
 * @author: zhangchao
 * @time: 2018-07-19 11:46
 **/
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "疾病自测列表")
public class ReportResultDto implements Serializable {
    private static final long serialVersionUID = 3444122154770040665L;
    /**
     * 数值
     */
    @ApiModelProperty(value = "数值", name = "value", dataType = "Long", required = true)
    private Long value;

    /**
     * 时间
     */
    @ApiModelProperty(value = "时间", name = "date", dataType = "String", required = true)
    private String date;
}
