package com.zqkh.webapi.context.service;

import com.zqkh.gene.feign.dto.GeneOrderDto;
import com.zqkh.gene.feign.dto.gene.CollectorDto;
import com.zqkh.webapi.context.dto.order.DeliverCollectorDto;
import com.zqkh.webapi.context.dto.order.DeliverCollectorOtherDto;
import com.zqkh.webapi.context.dto.order.GeneExpressDetailDto;
import com.zqkh.webapi.context.dto.order.GeneOrderListDto;

import java.util.List;

/**
 * 基因订单业务接口
 *
 * @author 东来
 * @create 2018/1/31 0031
 */
public interface GeneOrderService {

    /**
     * 根据成员编号查询基因订单列表
     * @param familyId:家庭成员编号
     * @return
     */
    List<GeneOrderListDto> getGeneOrderListByFamilyId(String familyId);

    GeneOrderDto CreateGeneOrder(GeneOrderDto geneOrdeDto);

    void deliverGood(DeliverCollectorDto deliverCollectorDto);

    List<CollectorDto> vaildCollectorNo(String collectorNo);

    GeneExpressDetailDto getOrderTrack(String orderId);

    void otherDeliverGood(DeliverCollectorOtherDto deliverCollectorOtherDto);
}
