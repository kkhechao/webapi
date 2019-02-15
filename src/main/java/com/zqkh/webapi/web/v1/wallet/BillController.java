package com.zqkh.webapi.web.v1.wallet;

import com.zqkh.common.result.PageResult;
import com.zqkh.wallet.feign.dto.*;
import com.zqkh.webapi.context.service.BillService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author hty
 * @create 2017-12-29 9:45
 **/
@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    BillService billService;

    @Autowired
    HttpServletRequest request;

    @ApiOperation(value = "api_bill_getBillList", notes = "获取账单列表")
    @GetMapping("/getBillList")
    PageResult<BillDto> getBillList(@NotNull(message = "pageIndex不能为空") @RequestParam("pageIndex") int pageIndex,
                                    @RequestParam("pageSize") int pageSize) {
        return billService.getBillList(pageIndex, pageSize);
    }

    @ApiOperation(value = "api_bill_getRechargeBillDetail", notes = "获取充值账单详情")
    @GetMapping("/getRechargeBillDetail")
    RechargeBillDetailDto getRechargeBillDetail(@RequestParam("billId") String billId) {
        return billService.getRechargeBillDetail(billId);
    }

    @ApiOperation(value = "api_bill_getWithdrawBillDetail", notes = "获取提现账单详情")
    @GetMapping("/getWithdrawBillDetail")
    WithdrawBillDetailDto getWithdrawBillDetail(@RequestParam("billId") String billId) {
        return billService.getWithdrawBillDetail(billId);
    }

    @ApiOperation(value = "api_bill_getConsumptionBillDetail", notes = "获取消费账单详情")
    @GetMapping("/getConsumptionBillDetail")
    ConsumptionBillDetailDto getConsumptionBillDetail(@RequestParam("billId") String billId) {
        return billService.getConsumptionBillDetail(billId);
    }

    @ApiOperation(value = "api_bill_getRewardBillDetail", notes = "获取达标奖励账单详情")
    @GetMapping("/getRewardBillDetail")
    RewardBillDetailDto getRewardBillDetail(@RequestParam("billId") String billId){
        return billService.getRewardBillDetail(billId);
    }

    @ApiOperation(value = "api_bill_createWithDrawBill", notes = "创建提现账单")
    @PostMapping("/createWithdrawBill")
    WithdrawResultDto createWithDrawBill(@Valid @RequestBody WithdrawBillDto withdrawBillDto) {
        return billService.createWithDrawBill(withdrawBillDto);
    }

    /**
     * @param rechargeBillDto
     * @param source          可能来源h5，下面生成的支付参数会不同
     * @return
     */
    @ApiOperation(value = "api_bill_createRechargeBill", notes = "创建充值账单")
    @PostMapping("/createRechargeBill")
    Map createRechargeBill(@RequestBody @Valid RechargeBillDto rechargeBillDto, String source) {
        return billService.createRechargeBill(request, rechargeBillDto, source);
    }

    @ApiOperation(value = "api_bill_getBillStatus", notes = "获取账单支付状态")
    @GetMapping("/getBillStatus")
    Map<String,String> getBillStatus(@RequestParam("billNumber")String billNumber){
        return billService.getBillStatus(billNumber);
    }
}
