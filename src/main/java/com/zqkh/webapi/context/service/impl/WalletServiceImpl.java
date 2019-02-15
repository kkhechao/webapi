package com.zqkh.webapi.context.service.impl;

import com.zqkh.common.result.PageResult;
import com.zqkh.member.feign.MemberClient;
import com.zqkh.wallet.feign.WalletClient;
import com.zqkh.wallet.feign.dto.SerialLogDto;
import com.zqkh.wallet.feign.dto.SerialLogRequest;
import com.zqkh.wallet.feign.dto.WalletInfoDto;
import com.zqkh.wallet.feign.dto.WithdrawInfoDto;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.common.BankInfo;
import com.zqkh.webapi.context.dto.wallet.MyWalletDto;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wenjie
 * @date 2017/12/26 0026 17:39
 */
@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletClient walletClient;
    @Autowired
    private MemberClient memberClient;

    @Override
    public WithdrawInfoDto getWithdraw() {
        JWTUserDto userDto = AuthManager.currentUser();
        WithdrawInfoDto withdrawDto = walletClient.withdraw(userDto.getUserId(),userDto.getAccountId());
        if(withdrawDto.getBankCardDto()!=null){
            withdrawDto.getBankCardDto().setSmallImg(BankInfo.BANK_MAP.get(withdrawDto.getBankCardDto().getCode()).getSmallImg());
        }
        return withdrawDto;
    }

    @Override
    public MyWalletDto getWallet() {
        JWTUserDto userDto = AuthManager.currentUser();
        WalletInfoDto wallet = walletClient.wallet(userDto.getAccountId());
        //IncomeStatisticDto income = memberClient.getToalAndTodayIncomeStatistics(userId);
        return new MyWalletDto(wallet.getTotalMoney(), wallet.getAvailableMoney(), wallet.getIntegral(),wallet.getWithdrawFreezeMoney());
    }

    @Override
    public PageResult<SerialLogDto> getIntegralDetail(Integer pageIndex, Integer pageSize) {
        JWTUserDto userDto = AuthManager.currentUser();

        SerialLogRequest serialLogRequest = new SerialLogRequest();
        serialLogRequest.setAccountId(userDto.getAccountId());
        serialLogRequest.setPageIndex(pageIndex);
        serialLogRequest.setPageSize(pageSize);

        return walletClient.integral(serialLogRequest);
    }
}
