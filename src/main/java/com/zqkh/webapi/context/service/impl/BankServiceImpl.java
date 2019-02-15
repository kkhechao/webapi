package com.zqkh.webapi.context.service.impl;

import com.zqkh.user.feign.UserClient;
import com.zqkh.wallet.feign.BankClient;
import com.zqkh.wallet.feign.dto.BankCardDto;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.common.BankInfo;
import com.zqkh.webapi.context.configurtion.properties.CloudConfigProperties;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wenjie
 * @date 2017/12/26 0026 11:07
 */
@Service
@Slf4j
public class BankServiceImpl implements BankService {

    public static final String EMPTY_STR = "";
    @Autowired
    private BankClient bankClient;
    @Autowired
    private UserClient userClient;

    @Autowired
    private CloudConfigProperties cloudConfigProperties;


    @Override
    public void addBank(BankCardDto bankCardDto) {
        JWTUserDto userDto = AuthManager.currentUser();
//        AuthInfoDto authInfoDto = userClient.getAuthInfoDto(userDto.getUserId());
//        if (authInfoDto == null) {
//            throw new BusinessException(BusinessExceptionEnum.USER_NOT_AUTH.getCode(), BusinessExceptionEnum.USER_NOT_AUTH.getMessage());
//        } else if (!bankCardDto.getName().equals(authInfoDto.getName())) {
//            throw new BusinessException(BusinessExceptionEnum.BINDCARD_NAME_NOT_TRUE.getCode(), BusinessExceptionEnum.BINDCARD_NAME_NOT_TRUE.getMessage());
//        }
        bankClient.addBank(bankCardDto, userDto.getAccountId());
    }

    @Override
    public void delBank(String cartNumber) {
        JWTUserDto userDto = AuthManager.currentUser();
        bankClient.delBank(cartNumber, userDto.getAccountId());
    }

    @Override
    public List<BankCardDto> getBanks() {
        JWTUserDto userDto = AuthManager.currentUser();
        List<BankCardDto> banks = bankClient.getBanks(userDto.getAccountId());
        banks.forEach(bank -> {
            bank.setSmallImg(BankInfo.BANK_MAP.get(bank.getCode()).getSmallImg());
        });
        return banks;
    }

    @Override
    public List<CloudConfigProperties.System.Bank> getBankList() {
        List<CloudConfigProperties.System.Bank> banks = cloudConfigProperties.getSystem().getBanks();
        banks.forEach(bank -> {
            bank.setImg(EMPTY_STR);
            bank.setInnerCode(EMPTY_STR);
        });
        return banks;
    }

}
