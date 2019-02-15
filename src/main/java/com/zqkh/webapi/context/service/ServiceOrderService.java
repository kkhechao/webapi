package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.order.feign.dto.OrderSimpleResponseDto;
import com.zqkh.order.feign.dto.ServiceOrderAppListDto;
import com.zqkh.order.feign.dto.ServiceOrderDetailDto;
import com.zqkh.webapi.context.dto.order.ConfirmServiceOrderDto;
import com.zqkh.webapi.context.dto.order.ItemIdNumDto;

public interface ServiceOrderService {

    ConfirmServiceOrderDto serviceOrderConfirm(String itemId);

    OrderSimpleResponseDto createServiceOrder(ItemIdNumDto itemIdNumDto);

    PageResult<ServiceOrderAppListDto> getServiceOrderList(String status, int pageIndex, int pageSize);

    ServiceOrderDetailDto getOrderDetail(String orderId);
}
