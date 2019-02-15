package com.zqkh.webapi.context.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author wenjie
 * @date 2018/1/18 0018 18:20
 */
@Getter
@Setter
@AllArgsConstructor
public final class IntegralDto {

    private BigDecimal integral;

    private Double percent;

}
