package com.zqkh.webapi.context.service;

import com.zqkh.shop.feign.dto.ShopApplicationDto;
import com.zqkh.shop.feign.dto.ShopApplicationResultDto;

/**
 * @author hty
 * @create 2018-06-07 14:31
 **/

public interface ShopApplicationService {
    void addShopApplication(ShopApplicationDto shopApplicationDto);

    ShopApplicationResultDto getUserShopApplicationResult();


    void updateShopApplication(ShopApplicationDto shopApplicationDto);
}
