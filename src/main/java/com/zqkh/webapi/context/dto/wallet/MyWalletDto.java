package com.zqkh.webapi.context.dto.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author wenjie
 * @date 2017/12/29 0029 15:20
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class MyWalletDto {

    private BigDecimal totalMoney;
    private BigDecimal availableMoney;
    private BigDecimal integral;
    private BigDecimal withdrawFreezeMoney;

}
