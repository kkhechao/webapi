package com.zqkh.webapi.context.dto.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zqkh.gene.feign.vo.GeneOrderStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 基因订单时间线
 *
 * @author 东来
 * @create 2018/6/29 0029
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "基因订单时间线")
public class GeneOrderTimeLineDto implements Serializable {

    /**
     * 状态信息
     */
    @ApiModelProperty(value = "状态信息", name = "message", dataType = "String")
    private String message;

    /**
     * 时间
     */
    @ApiModelProperty(value = "时间,时间戳", name = "time", dataType = "Date")
    private Date time;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", name = "status")
    private GeneOrderStatus status;

}
