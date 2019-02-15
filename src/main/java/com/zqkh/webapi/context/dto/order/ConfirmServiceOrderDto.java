package com.zqkh.webapi.context.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author hty
 * @create 2018-06-12 10:05
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmServiceOrderDto {

    private String itemId;

    private String itemTitle;

    private String itemCover;

    private BigDecimal itemMarketPrice;

    private BigDecimal itemMallPrice;

    private BigDecimal itemVipPrice;

    private String shopName;

    private String userPhone;

}
