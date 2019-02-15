package com.zqkh.webapi.context.service.impl;

import com.zqkh.common.result.PageResult;
import com.zqkh.item.feign.ItemClient;
import com.zqkh.item.feign.dto.ItemInfoInwardDto;
import com.zqkh.member.feign.MemberClient;
import com.zqkh.member.feign.dto.MemberDto;
import com.zqkh.member.feign.dto.MemberGoodsListDto;
import com.zqkh.order.feign.ExpressClient;
import com.zqkh.order.feign.OrderClient;
import com.zqkh.order.feign.dto.*;
import com.zqkh.order.feign.dto.bsystem.ExpressInfoDto;
import com.zqkh.order.feign.dto.express.TrackDto;
import com.zqkh.user.feign.UserClient;
import com.zqkh.user.feign.dto.AddressDto;
import com.zqkh.wallet.feign.WalletClient;
import com.zqkh.wallet.feign.dto.WalletInfoDto;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.dto.order.*;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wenjie
 * @date 2018/1/18 0018 17:25
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserClient userClient;
    @Autowired
    private ItemClient itemClient;
    @Autowired
    private WalletClient walletClient;
    @Autowired
    private OrderClient orderClient;
    @Autowired
    private ExpressClient expressClient;
    @Autowired
    private MemberClient memberClient;

    public static Map<String, BigDecimal> MEMBER_GOODS_PRICE_MAP = Collections.EMPTY_MAP;

    @PostConstruct
    public void memberGoodsCache() {
        try {
            List<MemberGoodsListDto> memberGooodsId = memberClient.getMemberGooodsId();
            MEMBER_GOODS_PRICE_MAP = memberGooodsId.stream().collect(Collectors.toMap(MemberGoodsListDto::getGoodsId, MemberGoodsListDto::getPrice));
        } catch (Exception e) {
            log.error("member获取资料获取及解析失败");
            e.printStackTrace();
        }
    }

    @Override
    public ConfirmOrderDto confirm(List<ItemIdNumDto> confirmItemDtoList) {
        JWTUserDto userDto = AuthManager.currentUser();
        //得到用户默认地址
        AddressDto addressDto = userClient.getDefaultAddress(userDto.getId());
        //得到用户积分
        BigDecimal integral = walletClient.wallet(userDto.getAccountId()).getIntegral();
        //todo 积分比列
        Double percent = 0.01d;

        List<ItemNumDto> itemNumDtoList = null;
        if (confirmItemDtoList != null && confirmItemDtoList.size() > 0) {
            Map<String, Integer> itemNumMap = confirmItemDtoList.stream().collect(Collectors.toMap(ItemIdNumDto::getItemId, ItemIdNumDto::getNum));

            //得到商品
            List<ItemInfoInwardDto> itemDtoList = itemClient.getUpItemListByIds(itemNumMap.keySet().toArray(new String[0]));

            //判断是否是会员商品，是则拿会员系统的价格
            itemDtoList = itemDtoList.stream().map(item -> {
                String itemId = item.getId();
                if (StringUtils.containsAny(itemId, MEMBER_GOODS_PRICE_MAP.keySet().toArray(new String[]{}))) {
                    item.setMallPrice(MEMBER_GOODS_PRICE_MAP.get(itemId));
                    return item;
                }
                return item;
            }).collect(Collectors.toList());

            //会员价格
            MemberDto member = memberClient.getMember(userDto.getId());
            if (member != null) {
                itemDtoList.stream().forEach(i -> i.setMallPrice(i.getVipPrice()));
            }
            itemNumDtoList = itemDtoList.stream().map(p -> new ItemNumDto(p, itemNumMap.get(p.getId()))).collect(Collectors.toList());
        }


        ConfirmOrderDto confirmOrderDto = new ConfirmOrderDto();
        confirmOrderDto.setAddressDto(addressDto);
        confirmOrderDto.setIntegralDto(new IntegralDto(integral, percent));
        confirmOrderDto.setItemNumDto(itemNumDtoList);

        return confirmOrderDto;
    }

    @Override
    public OrderSimpleResponseDto create(CreateOrderDto createOrderDto) {
        JWTUserDto userDto = AuthManager.currentUser();

        if (createOrderDto.getItemIdNumDtoList().size() <= 0) {
            throw new BusinessException(BusinessExceptionEnum.NO_ITEM_EXCEPTION.getCode(), BusinessExceptionEnum.NO_ITEM_EXCEPTION.getMessage());
        }

        BigDecimal integral = BigDecimal.ZERO;

        WalletInfoDto wallet = walletClient.wallet(userDto.getAccountId());
        //使用积分
        if (createOrderDto.getUseIntegral()) {
            integral = wallet.getIntegral();
        }

        String userId = userDto.getUserId();

        AddressDto addressDto = userClient.getAddressDetailById(userId, createOrderDto.getAddressId());

        AddressInfoDto addressInfoDto = new AddressInfoDto();
        addressInfoDto.setName(addressDto.getName());
        addressInfoDto.setPhone(addressDto.getPhone());
        addressInfoDto.setDetail(addressDto.getDetail());
        addressInfoDto.setCode(addressDto.getCode());

        //是否会员  会员价格不同
        MemberDto member = memberClient.getMember(userId);

        OrderRequestDto orderDto = new OrderRequestDto();
        orderDto.setMessage(createOrderDto.getMessage());
        orderDto.setBuyerId(userId);
        orderDto.setAddressInfoDto(addressInfoDto);
        orderDto.setVipFlag(member == null ? Boolean.FALSE : Boolean.TRUE);
        orderDto.setItemNumMap(createOrderDto.getItemIdNumDtoList().stream().collect(Collectors.toMap(ItemIdNumDto::getItemId, ItemIdNumDto::getNum)));
        orderDto.setIntegral(integral);

        return orderClient.createOrder(orderDto);
    }

    @Override
    public PageResult<MyOrderDto> myOrders(String status, int pageIndex, int pageSize) {
        JWTUserDto userDto = AuthManager.currentUser();
        return orderClient.getOrderByStatus(userDto.getUserId(), status, pageIndex, pageSize);
    }

    @Override
    public OrderDetailDto getOrderDetail(String orderId) {
        return orderClient.getOrderDetail(orderId);
    }

    @Override
    public ExpressDetailDto getOrderTrack(String orderId) {
        OrderDetailDto orderDetail = orderClient.getOrderDetail(orderId);
        if (orderDetail == null) {
            throw new BusinessException(BusinessExceptionEnum.NO_ORDER_EXCEPTION.getCode(), BusinessExceptionEnum.NO_ORDER_EXCEPTION.getMessage());
        }
        ExpressInfoDto expressInfoDto = orderDetail.getExpressInfoDto();
        List<TrackDto> track = null;
        if (expressInfoDto != null) {
            track = expressClient.getTrack(expressInfoDto.getCode(), expressInfoDto.getNumber());
        }
        Collections.reverse(track);
        return new ExpressDetailDto(expressInfoDto, track, orderDetail.getAddressInfoDto());
    }
}
