package com.zqkh.webapi.web.v1.shop;

import com.zqkh.shop.feign.dto.ShopApplicationDto;
import com.zqkh.shop.feign.dto.ShopApplicationResultDto;
import com.zqkh.webapi.context.service.ShopApplicationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hty
 * @create 2018-06-07 14:30
 **/
@RestController
public class ShopApplicationController {

    @Autowired
    private ShopApplicationService shopApplicationService;

    @ApiOperation(value = "api_add_shop_application")
    @PostMapping("/shop/addShopApplication")
    void addShopApplication(@RequestBody ShopApplicationDto shopApplicationDto){
        shopApplicationService.addShopApplication(shopApplicationDto);
    }

    @ApiOperation(value = "api_get_user_shop_application")
    @GetMapping("/shop/getUserShopApplicationResult")
    ShopApplicationResultDto getUserShopApplicationResult(){
        return shopApplicationService.getUserShopApplicationResult();
    }

    @ApiOperation(value = "api_update_shop_application")
    @PostMapping("/shop/updateShopApplication")
    void updateShopApplication(@RequestBody ShopApplicationDto shopApplicationDto){
        shopApplicationService.updateShopApplication(shopApplicationDto);
    }
}
