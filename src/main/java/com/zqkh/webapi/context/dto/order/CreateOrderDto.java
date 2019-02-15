package com.zqkh.webapi.context.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author wenjie
 * @date 2018/1/19 0019 11:17
 */
@Getter
@Setter
@AllArgsConstructor
public final class CreateOrderDto {

    private String addressId;

    private Boolean useIntegral = Boolean.FALSE;

    private List<ItemIdNumDto> itemIdNumDtoList;

    private String message;

}
