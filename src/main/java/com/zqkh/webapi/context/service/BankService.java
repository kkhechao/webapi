package com.zqkh.webapi.context.service;

import com.zqkh.wallet.feign.dto.BankCardDto;
import com.zqkh.webapi.context.configurtion.properties.CloudConfigProperties;

import java.util.List;

/**
 * @author wenjie
 * @date 2017/12/26 0026 11:07
 */
public interface BankService {
    void addBank(BankCardDto bankCardDto);

    void delBank(String cartNumber);

    List<BankCardDto> getBanks();

    List<CloudConfigProperties.System.Bank> getBankList();
}
