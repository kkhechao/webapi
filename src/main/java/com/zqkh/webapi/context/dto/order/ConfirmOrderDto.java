package com.zqkh.webapi.context.dto.order;

import com.zqkh.user.feign.dto.AddressDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author wenjie
 * @date 2018/1/18 0018 18:19
 */
@Getter
@Setter
public final class ConfirmOrderDto {
    private AddressDto addressDto;

    private IntegralDto integralDto;

    private List<ItemNumDto> itemNumDto;
}
