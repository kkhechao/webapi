package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.wallet.feign.dto.SerialLogDto;
import com.zqkh.wallet.feign.dto.WithdrawInfoDto;
import com.zqkh.webapi.context.dto.wallet.MyWalletDto;

/**
 * @author wenjie
 * @date 2017/12/26 0026 17:39
 */
public interface WalletService {
    WithdrawInfoDto getWithdraw();

    MyWalletDto getWallet();

    PageResult<SerialLogDto> getIntegralDetail(Integer pageIndex, Integer pageSize);
}
