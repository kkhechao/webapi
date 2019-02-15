package com.zqkh.webapi.web.v1.order;

import com.zqkh.common.result.PageResult;
import com.zqkh.order.feign.dto.MyOrderDto;
import com.zqkh.order.feign.dto.OrderDetailDto;
import com.zqkh.order.feign.dto.OrderSimpleResponseDto;
import com.zqkh.webapi.context.dto.order.ConfirmDto;
import com.zqkh.webapi.context.dto.order.ConfirmOrderDto;
import com.zqkh.webapi.context.dto.order.CreateOrderDto;
import com.zqkh.webapi.context.dto.order.ExpressDetailDto;
import com.zqkh.webapi.context.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @author wenjie
 * @date 2018/1/18 0018 17:24
 */
@RequestMapping("/order")
@RestController
@Log4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/confirm")
    @ApiOperation(value = "api_order_confirm")
    public ConfirmOrderDto confirm(@RequestBody ConfirmDto confirmDto) {
        return orderService.confirm(confirmDto.getConfirmItemDtoList());
    }

    @ApiOperation(value = "api_order_create")
    @PostMapping("/create")
    public OrderSimpleResponseDto create(@RequestBody CreateOrderDto createOrderDto) {
        return orderService.create(createOrderDto);
    }

    @ApiOperation(value = "api_order_my")
    @GetMapping("/my")
    public PageResult<MyOrderDto> myOrder(String status, int pageIndex, int pageSize) {
        return orderService.myOrders(status, pageIndex, pageSize);
    }

    @ApiOperation(value = "api_order_detail")
    @GetMapping("/detail")
    public OrderDetailDto getOrderDetail(String orderId) {
        return orderService.getOrderDetail(orderId);
    }

    @ApiOperation(value = "api_order_track")
    @GetMapping("/track")
    public ExpressDetailDto getOrderTrack(@NotNull String orderId) {
        return orderService.getOrderTrack(orderId);
    }
}
