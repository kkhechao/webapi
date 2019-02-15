package com.zqkh.webapi.context.service.impl;

import com.jovezhao.nest.starter.AppService;
import com.jovezhao.nest.utils.JsonUtils;
import com.zqkh.common.result.PageResult;
import com.zqkh.common.util.DateUtil;
import com.zqkh.member.feign.MemberClient;
import com.zqkh.member.feign.dto.MemberDto;
import com.zqkh.order.feign.OrderClient;
import com.zqkh.user.feign.UserClient;
import com.zqkh.user.feign.dto.InviterDto;
import com.zqkh.wallet.feign.BillClient;
import com.zqkh.wallet.feign.dto.*;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.BillService;
import com.zqkh.webapi.context.service.PayService;
import com.zqkh.webapi.context.utils.CusAccessObjectUtil;
import com.zqkh.webapi.context.utils.RedisCacheUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author hty
 * @create 2017-12-29 9:48
 **/
@AppService
public class BillServiceImpl implements BillService {

    public static final String SOURCE_FROM_H5 = "H5";
    @Autowired
    BillClient billClient;

    @Autowired
    PayService payService;

    @Autowired
    UserClient userClient;

    @Autowired
    OrderClient orderClient;

    @Autowired
    MemberClient memberClient;

    @Override
    public PageResult<BillDto> getBillList(int pageIndex, int pageSize) {
        JWTUserDto userDto = AuthManager.currentUser();
        return billClient.getBillList(userDto.getId(), pageIndex, pageSize);
    }

    @Override
    public RechargeBillDetailDto getRechargeBillDetail(String billId) {
        JWTUserDto userDto = AuthManager.currentUser();
        return billClient.getRechargeBillDetail(userDto.getId(), billId);
    }

    @Override
    public WithdrawBillDetailDto getWithdrawBillDetail(String billId) {
        JWTUserDto userDto = AuthManager.currentUser();
        WithdrawBillDetailDto withdrawBillDetailDto = billClient.getWithdrawBillDetail(userDto.getId(), billId);
        if (ObjectUtils.isEmpty(withdrawBillDetailDto.getWithdrawBankInfo().getType())) {
            withdrawBillDetailDto.setTitle("提现-个人账户");
        } else {
            withdrawBillDetailDto.setTitle(BankCardDto.Type.PRIVATE.toString().equals(withdrawBillDetailDto.getWithdrawBankInfo().getType()) ? "提现-个人账户" : "提现-对公账户");
        }

        withdrawBillDetailDto.setExpectTime(DateUtil.addDate(withdrawBillDetailDto.getCreateTime(), 0, 0, 5, 0, 0, 0, 0));
        String accountIdByPeopleId = userClient.getAccountIdByPeopleId(userDto.getId());
        withdrawBillDetailDto.setFundingDetails(withdrawBillDetailDto.getFundingDetails().stream().filter(p ->
                accountIdByPeopleId.equals(p.getAccountId())).collect(Collectors.toList()));
        withdrawBillDetailDto.getFundingDetails().forEach(p -> {
            p.setCreateTime(withdrawBillDetailDto.getCreateTime());
        });
        return withdrawBillDetailDto;
    }

    @Override
    public RewardBillDetailDto getRewardBillDetail(String billId) {
        JWTUserDto userDto = AuthManager.currentUser();
        return billClient.getRewardBillDetail(userDto.getId(), billId);
    }


    @Override
    public ConsumptionBillDetailDto getConsumptionBillDetail(String billId) {
        JWTUserDto userDto = AuthManager.currentUser();
        return billClient.getConsumptionBillDetail(userDto.getId(), billId);
    }

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Override
    public WithdrawResultDto createWithDrawBill(WithdrawBillDto withdrawBillDto) {
        JWTUserDto userDto = AuthManager.currentUser();
        String redisCaptcha = (String) redisCacheUtil.getCacheObject(withdrawBillDto.getWithdrawBillSMSDto().getPhone());
        if (withdrawBillDto.getWithdrawBillSMSDto().getCaptcha().equals(redisCaptcha)) {
            redisCacheUtil.deleteObject(withdrawBillDto.getWithdrawBillSMSDto().getPhone());
            withdrawBillDto.setUserId(userDto.getId());
            return billClient.createWithDrawBill(withdrawBillDto);
        } else {
            throw new BusinessException(BusinessExceptionEnum.CAPTCHA_NOT_TRUE.getCode(), BusinessExceptionEnum.CAPTCHA_NOT_TRUE.getMessage());
        }
    }

    @Override
    public Map createRechargeBill(HttpServletRequest request, RechargeBillDto rechargeBillDto, String source) {
        JWTUserDto userDto = AuthManager.currentUser();

        String userId = userDto.getId();
//        //因为有7天保护的情况，所以付款之前先判断是否有邀请关系
//        InviterDto inviterDto = userClient.getInviterUserId(userId);
//        if (inviterDto == null) {
//            throw new BusinessException(BusinessExceptionEnum.USER_NOT_ACTIVE.getCode(), BusinessExceptionEnum.USER_NOT_ACTIVE.getMessage());
//        }
//        //done

        rechargeBillDto.setUserId(userId);
        String billId = billClient.generateBillId();
        rechargeBillDto.setBillId(billId);
        if (RechargeBillDto.RechargeType.MALL_CONSUMPTION_RECHARGE.equals(rechargeBillDto.getRechargeType())) {
            rechargeBillDto.setRechargeAmount(orderClient.getOrderPrice(rechargeBillDto.getOrderNumber()));
        } else if (RechargeBillDto.RechargeType.AGENT_RECHARGE.equals(rechargeBillDto.getRechargeType())) {
            rechargeBillDto.setRechargeAmount(memberClient.getAgentOrderPrice(rechargeBillDto.getOrderNumber()));
        }
        Map resultMap = new ConcurrentHashMap(10);
        if (RechargeBillDto.RechargeChannel.WECHAT == rechargeBillDto.getRechargeChannel()) {
            if (StringUtils.isNotEmpty(source) && SOURCE_FROM_H5.equals(source)) {
                //h5重复支付问题 判断是否已经付款过成为会员了
                MemberDto member = memberClient.getMember(userId);
                if (member != null) {
                    throw new BusinessException(BusinessExceptionEnum.USER_ALREADY_VIP.getCode(), BusinessExceptionEnum.USER_ALREADY_VIP.getMessage());
                }
                resultMap = payService.wechatH5Pay(CusAccessObjectUtil.getRemoteAddr(request), rechargeBillDto.getRechargeAmount(), billId);
            } else {
                resultMap = payService.wechatPay(CusAccessObjectUtil.getRemoteAddr(request), rechargeBillDto.getRechargeAmount(), billId);
            }
            resultMap.put("rechargeBillNumber", billId);
            rechargeBillDto.setChannelSerial(resultMap.get("prepayid").toString());
            rechargeBillDto.setRequestParam(JsonUtils.toJsonString(rechargeBillDto));
            billClient.createRechargeBill(rechargeBillDto);
        } else if (RechargeBillDto.RechargeChannel.ALIPAY == rechargeBillDto.getRechargeChannel()) {
            resultMap = payService.alipay(rechargeBillDto.getRechargeAmount(), billId);
            resultMap.put("rechargeBillNumber", billId);
            rechargeBillDto.setRequestParam(JsonUtils.toJsonString(rechargeBillDto));
            billClient.createRechargeBill(rechargeBillDto);
        } else if (RechargeBillDto.RechargeChannel.BALANCE == rechargeBillDto.getRechargeChannel()) {
            billClient.createBalanceConsumptionBill(rechargeBillDto);
        }
        return resultMap;
    }


    @Override
    public Map<String, String> getBillStatus(String billNumber) {
        return billClient.getBillStatus(billNumber);
    }

}
