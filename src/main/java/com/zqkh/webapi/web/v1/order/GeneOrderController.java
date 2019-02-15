package com.zqkh.webapi.web.v1.order;


import com.zqkh.gene.feign.dto.GeneOrderDto;
import com.zqkh.gene.feign.dto.gene.CollectorDto;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.dto.order.DeliverCollectorDto;
import com.zqkh.webapi.context.dto.order.DeliverCollectorOtherDto;
import com.zqkh.webapi.context.dto.order.GeneExpressDetailDto;
import com.zqkh.webapi.context.dto.order.GeneOrderListDto;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.service.GeneOrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 基因订单控制层
 *
 * @author 东来
 * @create 2018/1/31 0031
 */
@RestController
@RequestMapping("/order/gene")
public class GeneOrderController {


    @Resource
    private GeneOrderService geneOrderService;

    /**
     * 根据成员编号查询基因订单列表
     * @param familyId:家庭成员编号
     * @return
     */
    @GetMapping("/list")
    @Anonymous
    @ApiOperation(value = "api_gene_order_list", notes = "基因订单列表")
    @ApiImplicitParam(name = "familyId",defaultValue = "0",value="家庭成员编号",paramType="query",dataType="String")
    List<GeneOrderListDto> getGeneOrderListByFamilyId(@RequestParam(value = "familyId",required = false) String familyId){
        return geneOrderService.getGeneOrderListByFamilyId(familyId);
    };
    /**
     * 创建基因订单
     * @param geneOrderDto
     * @return
     */
    @PostMapping("/createGeneOrder")
    @ApiOperation(value = "api_gene_order_create")
    GeneOrderDto CreateGeneOrder(@RequestBody  GeneOrderDto geneOrderDto){
        return geneOrderService.CreateGeneOrder(geneOrderDto);
    };

    @PostMapping("/deliverGood")
    @ApiOperation(value = "api_gene_order_deliverGood")
    void deliverGood(@RequestBody DeliverCollectorDto deliverCollectorDto){
        geneOrderService.deliverGood(deliverCollectorDto);
    };


    /**
     * 其他方式回寄
     *
     * @param deliverCollectorOtherDto
     */
    @PostMapping("/otherDeliverGood")
    @ApiOperation(value = "api_gene_order_otherDeliverGood")
    void otherDeliverGood(@RequestBody DeliverCollectorOtherDto deliverCollectorOtherDto) {
        geneOrderService.otherDeliverGood(deliverCollectorOtherDto);
    }

    ;

    @GetMapping("/vaildCollectorNo")
    @ApiOperation(value = "api_gene_order_vaildCollectorNo")
    List<CollectorDto> vaildCollectorNo(@RequestParam("collectorNo") String collectorNo){
        List<CollectorDto> collectorDtoList = geneOrderService.vaildCollectorNo(collectorNo);
        if(collectorDtoList==null){
            throw  new BusinessException(BusinessExceptionEnum.ERRRO_COLECTORNO_EXCEPTION.getCode(),BusinessExceptionEnum.ERRRO_COLECTORNO_EXCEPTION.getMessage());
        }
        return collectorDtoList;
    }

    @GetMapping("/track")
    @ApiOperation(value = "api_gene_order_track")
    public GeneExpressDetailDto getOrderTrack(@NotNull String orderId) {
        return geneOrderService.getOrderTrack(orderId);
    }


}
