package com.zqkh.webapi.context.service;

import com.zqkh.user.feign.dto.*;
import com.zqkh.webapi.context.dto.user.*;

import java.util.List;
import java.util.Map;

/**
 * @author wenjie
 * @date 2017/12/11 0011 11:54
 */
public interface UserService {

    Map<String, Object> captchaLoginByMySelf(CaptchaLoginDto dto) ;

    Map<String, Object> wechatBindingLogin(WechatLoginDto dto);

    UserDto wechatBinding(WechatIdDto wechatIdDto);

    Map<String, Object> wechatLogin(WechatIdDto wechatIdDto);


    List<AddressDto> getAddressList();

    AddressDto updateAddress(AddressDto addressDto);

    void deleteAddress(String addressId);

    AddressDto addAddress(AddressDto addressDto);

    void setDefaultAddress(String addressId);

    void activationAccount(String code);

    UserDto updateBodyInfo(BodyInfoDto bodyInfoDto);

    UserDto updateUserInfo(UserDto userDto);

    Map<String, Object> getCenterInfo();

    Map<String, Object> getUserInfo();

    UserDto updateUserAvatar(AvatarDto avatarDto);

    CodeConfirmDto getInviteCodeUserName(String code);

    UserDtoForSupperVIP getUserInfoSupper();

    String getInivterPhone();

    UserDto getUserInfoByInviteCode(String code);

    InviterDto getInviterInfo();

}
