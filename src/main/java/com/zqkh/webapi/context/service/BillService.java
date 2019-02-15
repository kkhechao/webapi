package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.wallet.feign.dto.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface BillService {

    PageResult<BillDto> getBillList(int pageIndex, int pageSize);

    RechargeBillDetailDto getRechargeBillDetail(String billId);

    WithdrawBillDetailDto getWithdrawBillDetail(String billId);

    ConsumptionBillDetailDto getConsumptionBillDetail(String billId);

    WithdrawResultDto createWithDrawBill(WithdrawBillDto withdrawBillDto);

    Map<String,String> getBillStatus(String billNumber);

    Map createRechargeBill(HttpServletRequest request, RechargeBillDto rechargeBillDto, String source);

    RewardBillDetailDto getRewardBillDetail(String billId);

}
