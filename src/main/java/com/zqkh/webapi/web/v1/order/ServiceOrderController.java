package com.zqkh.webapi.web.v1.order;

import com.zqkh.common.result.PageResult;
import com.zqkh.order.feign.dto.OrderSimpleResponseDto;
import com.zqkh.order.feign.dto.ServiceOrderAppListDto;
import com.zqkh.order.feign.dto.ServiceOrderDetailDto;
import com.zqkh.webapi.context.dto.order.ConfirmServiceOrderDto;
import com.zqkh.webapi.context.dto.order.ItemIdNumDto;
import com.zqkh.webapi.context.service.ServiceOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hty
 * @create 2018-06-12 10:29
 **/
@RestController
@RequestMapping("/serviceOrder")
public class ServiceOrderController {

    @Autowired
    private ServiceOrderService serviceOrderService;

    @GetMapping("/serviceOrderConfirm")
    @ApiOperation(value = "api_service_order_confirm")
    public ConfirmServiceOrderDto serviceOrderConfirm(@RequestParam("itemId") String itemId) {
        return serviceOrderService.serviceOrderConfirm(itemId);
    }

    @ApiOperation(value = "api_create_service_order")
    @PostMapping("/serviceOrder/createServiceOrder")
    public OrderSimpleResponseDto createServiceOrder(@RequestBody ItemIdNumDto itemIdNumDto){
        return serviceOrderService.createServiceOrder(itemIdNumDto);
    }

    @ApiOperation(value = "api_get_service_order_list")
    @GetMapping("/serviceOrder/getServiceOrderList")
    PageResult<ServiceOrderAppListDto> getServiceOrderList(@RequestParam(value = "status",required = false)
            String status, @RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize){
        return serviceOrderService.getServiceOrderList(status,pageIndex,pageSize);
    }

    @ApiOperation(value = "api_get_service_order_detail")
    @GetMapping("/serviceOrder/getServiceOrderDetail")
    ServiceOrderDetailDto getOrderDetail(@RequestParam("orderId") String orderId){
        return serviceOrderService.getOrderDetail(orderId);
    }
}
