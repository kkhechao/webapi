package com.zqkh.webapi.web.v1.wallet;

import com.zqkh.common.result.PageResult;
import com.zqkh.wallet.feign.dto.SerialLogDto;
import com.zqkh.wallet.feign.dto.WithdrawInfoDto;
import com.zqkh.webapi.context.dto.wallet.MyWalletDto;
import com.zqkh.webapi.context.service.WalletService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author wenjie
 * @date 2017/12/26 0026 17:38
 */
@RestController
@RequestMapping("/wallet")
@Validated
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/")
    @ApiOperation(value = "api_wallet")
    public MyWalletDto wallet() {
        return walletService.getWallet();
    }

    /**
     * 提现
     */
    @GetMapping("/withdraw")
    @ApiOperation(value = "api_wallet_withdraw")
    public WithdrawInfoDto withdraw() {
        return walletService.getWithdraw();
    }

    /**
     * 积分明细
     *
     * @return
     */
    @GetMapping("/integral")
    @ApiOperation(value = "api_wallet_integral")
    public PageResult<SerialLogDto> integralDetail(@NotNull Integer pageIndex, @NotNull Integer pageSize) {
        return walletService.getIntegralDetail(pageIndex, pageSize);
    }
}
