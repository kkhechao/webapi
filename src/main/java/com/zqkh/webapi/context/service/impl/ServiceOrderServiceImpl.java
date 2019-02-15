package com.zqkh.webapi.context.service.impl;

import com.zqkh.common.result.PageResult;
import com.zqkh.item.feign.ItemClient;
import com.zqkh.item.feign.dto.ItemInfoToAppDto;
import com.zqkh.member.feign.MemberClient;
import com.zqkh.member.feign.dto.MemberDto;
import com.zqkh.order.feign.ServiceOrderClient;
import com.zqkh.order.feign.dto.OrderSimpleResponseDto;
import com.zqkh.order.feign.dto.ServiceOrderAppListDto;
import com.zqkh.order.feign.dto.ServiceOrderCreateDto;
import com.zqkh.order.feign.dto.ServiceOrderDetailDto;
import com.zqkh.shop.feign.ShopApplicationClient;
import com.zqkh.user.feign.UserClient;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.dto.order.ConfirmServiceOrderDto;
import com.zqkh.webapi.context.dto.order.ItemIdNumDto;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.ServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hty
 * @create 2018-06-12 10:31
 **/
@Service
public class ServiceOrderServiceImpl implements ServiceOrderService {

    @Autowired
    private UserClient userClient;
    @Autowired
    private ItemClient itemClient;
    @Autowired
    private MemberClient memberClient;
    @Autowired
    private ServiceOrderClient serviceOrderClient;

    @Autowired
    private ShopApplicationClient shopApplicationClient;

    @Override
    public ConfirmServiceOrderDto serviceOrderConfirm(String itemId) {
        ConfirmServiceOrderDto confirmServiceOrderDto = new ConfirmServiceOrderDto();
        JWTUserDto userDto = AuthManager.currentUser();
        ItemInfoToAppDto itemInfoDtoToApp = itemClient.getItemInfoDtoToApp(itemId);
        confirmServiceOrderDto.setShopName(shopApplicationClient.getShopNameById(itemInfoDtoToApp.getSupplierId()));
        confirmServiceOrderDto.setItemId(itemId);
        confirmServiceOrderDto.setItemMallPrice(itemInfoDtoToApp.getMallPrice());
        confirmServiceOrderDto.setItemMarketPrice(itemInfoDtoToApp.getMarketPrice());
        confirmServiceOrderDto.setItemVipPrice(itemInfoDtoToApp.getVipPrice());
        confirmServiceOrderDto.setItemTitle(itemInfoDtoToApp.getTitle());
        confirmServiceOrderDto.setItemCover(itemInfoDtoToApp.getSurfacePhoto());
        confirmServiceOrderDto.setUserPhone(userClient.getUserInfo(userDto.getUserId()).getPhone());
        return confirmServiceOrderDto;
    }

    @Override
    public OrderSimpleResponseDto createServiceOrder(ItemIdNumDto itemIdNumDto) {
        JWTUserDto userDto = AuthManager.currentUser();
        String userId = userDto.getUserId();
        MemberDto member = memberClient.getMember(userId);
        ItemInfoToAppDto itemInfoDtoToApp = itemClient.getItemInfoDtoToApp(itemIdNumDto.getItemId());
        ServiceOrderCreateDto serviceOrderCreateDto = new ServiceOrderCreateDto();
        serviceOrderCreateDto.setBuyerId(userId);
        serviceOrderCreateDto.setItemCount(itemIdNumDto.getNum());
        serviceOrderCreateDto.setVipFlag(member == null ? Boolean.FALSE : Boolean.TRUE);
        serviceOrderCreateDto.setShopId(itemInfoDtoToApp.getSupplierId());
        serviceOrderCreateDto.setItemId(itemIdNumDto.getItemId());
        return serviceOrderClient.createServiceOrder(serviceOrderCreateDto);
    }

    @Override
    public PageResult<ServiceOrderAppListDto> getServiceOrderList(String status, int pageIndex, int pageSize) {
        JWTUserDto userDto = AuthManager.currentUser();
        String userId = userDto.getUserId();
        return serviceOrderClient.getServiceOrderList(userId,status,pageIndex,pageSize);
    }

    @Override
    public ServiceOrderDetailDto getOrderDetail(String orderId) {
        return serviceOrderClient.getOrderDetail(orderId);
    }
}
