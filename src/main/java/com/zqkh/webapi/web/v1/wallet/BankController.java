package com.zqkh.webapi.web.v1.wallet;

import com.zqkh.wallet.feign.dto.BankCardDto;
import com.zqkh.webapi.context.configurtion.properties.CloudConfigProperties;
import com.zqkh.webapi.context.service.BankService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author wenjie
 * @date 2017/12/26 0026 10:55
 */
@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankService bankService;


    @PostMapping("/add")
    @ApiOperation(value = "api_wallet_addBank")
    public void addBank(@RequestBody @Valid BankCardDto bankCardDto) {
        bankService.addBank(bankCardDto);
    }

    @PostMapping("/del")
    @ApiOperation(value = "api_wallet_delBank")
    public void delBank(@RequestBody @NotNull @Size(min = 16, max = 19) String number) {
        bankService.delBank(number);
    }

    @GetMapping("/")
    @ApiOperation(value = "api_wallet_getBanks")
    public List<BankCardDto> getBanks() {
        return bankService.getBanks();
    }

    @GetMapping("/list")
    @ApiOperation(value = "api_wallet_bankList")
    public List<CloudConfigProperties.System.Bank> getBankList() {
        return bankService.getBankList();
    }

}
