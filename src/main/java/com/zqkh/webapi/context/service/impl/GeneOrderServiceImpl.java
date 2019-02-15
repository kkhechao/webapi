package com.zqkh.webapi.context.service.impl;

import com.zqkh.express.feign.ExpressClient;
import com.zqkh.express.feign.dto.TrackDto;
import com.zqkh.gene.feign.GeneOrderClient;
import com.zqkh.gene.feign.dto.AddressInfoDto;
import com.zqkh.gene.feign.dto.GeneOrderDto;
import com.zqkh.gene.feign.dto.bsystem.ExpressInfoDto;
import com.zqkh.gene.feign.dto.gene.*;
import com.zqkh.user.feign.UserClient;
import com.zqkh.user.feign.dto.AddressDto;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.dto.order.*;
import com.zqkh.webapi.context.dto.order.GeneOrderListDto;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.GeneOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基因订单业务实现层
 *
 * @author 东来
 * @create 2018/1/31 0031
 */
@Service
public class GeneOrderServiceImpl implements GeneOrderService {

    @Resource
    private GeneOrderClient geneOrderClient;
    @Autowired
    private ExpressClient expressClient;

    @Autowired
    UserClient userClient;


    @Override
    public List<GeneOrderListDto> getGeneOrderListByFamilyId(String familyId) {
        if(ObjectUtils.isEmpty(familyId)){
            throw new BusinessException(BusinessExceptionEnum.FAMILYID_IS_NULL.getCode(),"请选择一个成员");
        }

        List<GenOrderListDto> list= geneOrderClient.getGenOrderListByFamilyId(familyId);

        List<GeneOrderListDto> geneOrderListDtos=new ArrayList<>();
        if(ObjectUtils.isEmpty(list)){
            return geneOrderListDtos;
        }
        geneOrderListDtos =list.stream().map(m->{
            //处理时间线
            List<GeneOrderTimeLineDto> timeLineDtoList = null;
            if (!ObjectUtils.isEmpty(m.getTimeLine())) {
                timeLineDtoList = m.getTimeLine().stream().map(z -> {
                    GeneOrderTimeLineDto geneOrderTimeLineDto = GeneOrderTimeLineDto.builder()
                            .time(z.getTime())
                            .status(z.getStatus())
                            .message(z.getMessage())
                            .build();
                    return geneOrderTimeLineDto;
                }).sorted(Comparator.comparing(GeneOrderTimeLineDto::getTime)).collect(Collectors.toList());
            }

            return GeneOrderListDto.builder().id(m.getId())
                    .closeCause(m.getCloseCause())
                    .otherDeliver(m.getOtherDeliver())
                    .logisticsNo(m.getLogisticsNo())
                    .packageName(m.getPackageName())
                    .status(GeneOrderListDto.Status.getStatus(m.getStatus().name()))
                    .timeLine(timeLineDtoList)
                    .build();
        }).collect(Collectors.toList());

        return geneOrderListDtos;
    }


    @Override
    public GeneOrderDto CreateGeneOrder(GeneOrderDto geneOrdeDto) {
        JWTUserDto userDto = AuthManager.currentUser();
        geneOrdeDto.setUser(userDto.getUserId());
        GeneOrderDto order = geneOrderClient.createOrder(geneOrdeDto);
        return order;
    }

    @Override
    public void otherDeliverGood(DeliverCollectorOtherDto dto) {
        //JWTUserDto userDto = AuthManager.currentUser();
        DeliverGoodOtherWayDto deliverGoodOtherWayDto = new DeliverGoodOtherWayDto();
        deliverGoodOtherWayDto.setGeneOrder(dto.getGeneOrder());
        deliverGoodOtherWayDto.setRemark(dto.getRemark());
        deliverGoodOtherWayDto.setUser("c0044db2f430e459b875b9b2ff6c187de");
        geneOrderClient.otherDeliverGood(deliverGoodOtherWayDto);
    }

    @Override
    public void deliverGood(DeliverCollectorDto deliverCollectorDto) {
        JWTUserDto userDto = AuthManager.currentUser();
        String userId = userDto.getUserId();
        String address = deliverCollectorDto.getAddress();

        //寄件人地址
        AddressDto addressDto = userClient.getAddressDetailById(userId,address);
        AddressInfoDto addressInfoDto = new AddressInfoDto();
        addressInfoDto.setDetail(addressDto.getDetail());
        addressInfoDto.setPhone(addressDto.getPhone());
        addressInfoDto.setName(addressDto.getName());
        addressInfoDto.setCode(addressDto.getCode());

        DeliverGoodDto deliverGoodDto = new DeliverGoodDto();
        deliverGoodDto.setAddressInfoDto(addressInfoDto);
        deliverGoodDto.setGeneOrder(deliverCollectorDto.getGeneOrder());
        deliverGoodDto.setUser(userId);
        deliverGoodDto.setStartTime(deliverCollectorDto.getStartTime());
        deliverGoodDto.setEndTime(deliverCollectorDto.getEndTime());
        geneOrderClient.deliverGood(deliverGoodDto);

    }

    @Override
    public List<CollectorDto> vaildCollectorNo(String collectorNo) {
        List<CollectorDto> collectorDtoList = geneOrderClient.vaildCollectorNo(collectorNo);
        return collectorDtoList;
    }

    @Override
    public GeneExpressDetailDto getOrderTrack(String orderId) {
        GenOrderDetailDto genOrderDetail = geneOrderClient.getGenOrderDetail(orderId);
        if (genOrderDetail == null) {
            throw new BusinessException(BusinessExceptionEnum.NO_GENE_ORDER_EXCEPTION.getCode(), BusinessExceptionEnum.NO_GENE_ORDER_EXCEPTION.getMessage());
        }
        ExpressInfoDto expressInfoDto = genOrderDetail.getExpressInfoDto();
        List<TrackDto> track = null;
        if (expressInfoDto != null) {
            track = expressClient.getTrack(expressInfoDto.getCode(), expressInfoDto.getNumber());
        }
        Collections.reverse(track);
        return new GeneExpressDetailDto(expressInfoDto, track, genOrderDetail.getAddressInfoDto());
    }
}
