package com.zqkh.webapi.context.common;

import com.zqkh.webapi.context.configurtion.properties.CloudConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.zqkh.webapi.context.configurtion.properties.CloudConfigProperties.System.Bank;

/**
 * @author wenjie
 * @date 2018/1/4 0004 15:48
 */
@Component
public final class BankInfo {

    @Autowired
    private CloudConfigProperties cloudConfigProperties;

    public static Map<String, Bank> BANK_MAP;

    @PostConstruct
    private void init() {
        //初始化银行卡对应背景数据信息
        List<Bank> banks = cloudConfigProperties.getSystem().getBanks();
        BANK_MAP = banks.stream().collect(Collectors.toMap(Bank::getCode, bank -> bank));
    }
}
