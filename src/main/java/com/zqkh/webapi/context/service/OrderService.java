package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.order.feign.dto.MyOrderDto;
import com.zqkh.order.feign.dto.OrderDetailDto;
import com.zqkh.order.feign.dto.OrderSimpleResponseDto;
import com.zqkh.webapi.context.dto.order.ConfirmOrderDto;
import com.zqkh.webapi.context.dto.order.CreateOrderDto;
import com.zqkh.webapi.context.dto.order.ExpressDetailDto;
import com.zqkh.webapi.context.dto.order.ItemIdNumDto;

import java.util.List;

/**
 * @author wenjie
 * @date 2018/1/18 0018 17:25
 */
public interface OrderService {
    ConfirmOrderDto confirm(List<ItemIdNumDto> confirmItemDtoList);

    OrderSimpleResponseDto create(CreateOrderDto createOrderDto);

    PageResult<MyOrderDto> myOrders(String status, int pageIndex, int pageSize);

    OrderDetailDto getOrderDetail(String orderId);

    ExpressDetailDto getOrderTrack(String orderId);
}
