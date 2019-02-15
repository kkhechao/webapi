package com.zqkh.webapi.context.service.impl;

import com.jovezhao.nest.starter.AppService;
import com.zqkh.shop.feign.ShopApplicationClient;
import com.zqkh.shop.feign.dto.ApplicantInfoDto;
import com.zqkh.shop.feign.dto.ShopApplicationDto;
import com.zqkh.shop.feign.dto.ShopApplicationResultDto;
import com.zqkh.user.feign.UserClient;
import com.zqkh.user.feign.dto.UserDto;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.ShopApplicationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hty
 * @create 2018-06-07 14:31
 **/
@AppService
public class ShopApplicationServiceImpl implements ShopApplicationService {
    @Autowired
    private ShopApplicationClient shopApplicationClient;

    @Autowired
    private UserClient userClient;

    @Override
    public void addShopApplication(ShopApplicationDto shopApplicationDto) {
        JWTUserDto userDto = AuthManager.currentUser();
        shopApplicationDto.setUserId(userDto.getUserId());
        shopApplicationDto.setType("FROMC");
        UserDto userInfo = userClient.getUserInfo(userDto.getUserId());
        ApplicantInfoDto applicantInfoDto = new ApplicantInfoDto();
        applicantInfoDto.setCreateTime(userInfo.getCreateTime());
        applicantInfoDto.setIdNumber(userInfo.getAuthInfo().getIdCard());
        applicantInfoDto.setPhone(userInfo.getPhone());
        applicantInfoDto.setRealName(userInfo.getAuthInfo().getName());
        applicantInfoDto.setSex(ApplicantInfoDto.Sex.valueOf(userInfo.getSex().toString()));
        applicantInfoDto.setVipLevel(ApplicantInfoDto.VipLevel.valueOf(userInfo.getAgencyLevel()));
        shopApplicationDto.setApplicantInfo(applicantInfoDto);
        shopApplicationClient.addShopApplication(shopApplicationDto);
    }

    @Override
    public ShopApplicationResultDto getUserShopApplicationResult() {
        JWTUserDto userDto = AuthManager.currentUser();
        return shopApplicationClient.getUserShopApplicationResult(userDto.getUserId());
    }

    @Override
    public void updateShopApplication(ShopApplicationDto shopApplicationDto) {
        shopApplicationClient.updateShopApplication(shopApplicationDto);
    }
}
