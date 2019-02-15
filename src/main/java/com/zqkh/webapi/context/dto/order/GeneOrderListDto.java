package com.zqkh.webapi.context.dto.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 基因订单列表DTO
 *
 * @author 东来
 * @create 2018/1/31 0031
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "基因订单列表")
public class GeneOrderListDto {

    /**
     * 订单状态
     */
    @ApiModel(description = "基因订单状态")
    public enum Status{
        /**
         * 待发货
         */
        WAIT_DELIVERED,
        /**
         * 待收货
         */
        WAIT_RECEIVE,

        /**
         * 已收货
         */
        RECEIVE,


        /**
         * 送检
         */
        INSPECTION,

        /**
         * DNA提取
         */
        DNA_EXTRACTION,


        /**
         * 建库
         */
        CREATE_DATABASE,

        /**
         * 测序
         */
        SEQUENCE,

        /**
         * 待审核
         */
        WAIT_AUDITING,
        /**
         * 完成
         */
        DONE,

        /**
         * 关闭
         */
        CLOSE;

        public static Status getStatus(String statusName){
            for (Status status:Status.values()) {
                if(status.name().equals(statusName)){
                    return status;
                }
            }
            return null;
        }

    }

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号",name = "id",dataType = "String",required = false)
    private String id;

    /**
     * 套餐名
     */
    @ApiModelProperty(value = "套餐名",name = "packageName",dataType = "String",required = false)
    private String packageName;

    /**
     * 检测状态
     */
    @ApiModelProperty(value = "检测状态( WAIT_DELIVERED(待发货),WAIT_RECEIVE(待收货),RECEIVE(已收货),INSPECTION(送检),DNA_EXTRACTION(DNA测序),CREATE_DATABASE(建库),SEQUENCE(测序),WAIT_AUDITING(待审核),DONE(完成),CLOSE(失效或关闭))", name = "status", dataType = "Status", required = true)
    private Status status;

    /**
     * 物流单号
     */
    @ApiModelProperty(value = "物流单号",name = "logisticsNo",dataType = "String",required = false)
    private String logisticsNo;

    /**
     * 是否其他方式回寄
     */
    @ApiModelProperty(value = "是否其他方式回寄", name = "otherDeliver", dataType = "boolean", required = true)
    private boolean otherDeliver = false;


    /**
     * 失效关闭原因
     */
    @ApiModelProperty(value = "关闭失效原因", name = "closeCause", dataType = "String")
    private String closeCause;

    /**
     * 时间线
     */
    @ApiModelProperty(value = "时间线", name = "timeLine", dataType = "GeneTimeLineDto")
    private List<GeneOrderTimeLineDto> timeLine;
}
