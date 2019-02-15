package com.zqkh.webapi.context.service.impl;


import com.google.common.collect.Maps;
import com.jovezhao.nest.ddd.event.EventBus;
import com.jovezhao.nest.starter.AppService;
import com.zqkh.file.eventdto.AppendFileUseRecordDto;
import com.zqkh.member.feign.MemberClient;
import com.zqkh.member.feign.dto.MemberDto;
import com.zqkh.user.feign.UserClient;
import com.zqkh.user.feign.dto.*;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.configurtion.properties.CloudConfigProperties;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.dto.user.*;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.UserService;
import com.zqkh.webapi.context.utils.JwtTokenContextUtil;
import com.zqkh.webapi.context.utils.RedisCacheUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenjie
 * @date 2017/12/11 0011 11:55
 */
@AppService
public class UserServiceImpl implements UserService {

    public static final String TOKEN = "token";
    public static final String USER = "user";
    public static final String SERVER_TIMESTAMP = "timestamp";
    public static final String APPLE_TEST_ACCOUNT = "13800000000";
    public static final String APPLE_TEST_ACCOUNT_CAPTCHA = "1470";

    @Autowired
    private JwtTokenContextUtil jwtTokenContext;
    @Autowired
    private RedisCacheUtil redisCacheUtil;
    @Autowired
    private UserClient userClient;

    @Autowired
    private CloudConfigProperties cloudConfigProperties;

    @Autowired
    MemberClient memberClient;


    private boolean validCaptcha(String phone,String inputCaptcha){
        String captcha = (String) redisCacheUtil.getCacheObject(phone);
//        if (StringUtils.isEmpty(captcha)) {
//            throw new BusinessException(BusinessExceptionEnum.CAPTCHA_NOT_TRUE.getCode(), BusinessExceptionEnum.CAPTCHA_NOT_TRUE.getMessage());
//        }

        if (cloudConfigProperties.getSystem().getUniversalCodeSwitch() && cloudConfigProperties.getSystem().getUniversalCode().equals(inputCaptcha)) {
            return true;
        } else if (inputCaptcha.equals(captcha) || (APPLE_TEST_ACCOUNT.equals(phone) && APPLE_TEST_ACCOUNT_CAPTCHA.equals(inputCaptcha))) {
            return true;
        } else {
            throw new BusinessException(BusinessExceptionEnum.CAPTCHA_NOT_TRUE.getCode(), BusinessExceptionEnum.CAPTCHA_NOT_TRUE.getMessage());
        }
    }

    @Override
    public Map<String, Object> captchaLoginByMySelf(CaptchaLoginDto dto) {
        validCaptcha(dto.getPhone(),dto.getCaptcha());
        UserDto userDto = userClient.loginAndActivationByMySelf(new MySelfLoginFeignDto(dto.getPhone(), dto.getCode()));
        //delete redis captcha
        redisCacheUtil.deleteObject(dto.getPhone());
        return toLoginMap(userDto);
    }

    private HashMap<String, Object> toLoginMap(UserDto userDto) {
        HashMap<String, Object> loginResultMap = getLoginResultMap(userDto);
        loginResultMap.put(SERVER_TIMESTAMP, System.currentTimeMillis());
        return loginResultMap;
    }

    @Override
    public Map<String, Object> wechatBindingLogin(WechatLoginDto dto) {
        String phone = dto.getPhone();
        String unionId = dto.getUnionId();
        String openId = dto.getOpenId();
        String captcha = (String) redisCacheUtil.getCacheObject(phone);
        if (StringUtils.isEmpty(captcha)) {
            throw new BusinessException(BusinessExceptionEnum.CAPTCHA_NOT_TRUE.getCode(), BusinessExceptionEnum.CAPTCHA_NOT_TRUE.getMessage());
        }

        if (dto.getCaptcha().equals(captcha)) {
            UserDto userDto = userClient.wechatBindingLogin(openId, unionId, dto.getPhone(), dto.getAvatar(), dto.getNickName());
            //delete redis captcha
            redisCacheUtil.deleteObject(phone);

            return getLoginResultMap(userDto);
        } else {
            throw new BusinessException(BusinessExceptionEnum.CAPTCHA_NOT_TRUE.getCode(), BusinessExceptionEnum.CAPTCHA_NOT_TRUE.getMessage());
        }
    }

    @Override
    public UserDto wechatBinding(WechatIdDto wechatIdDto) {
        String unionId = wechatIdDto.getUnionId();
        String openId = wechatIdDto.getOpenId();
        UserDto userDto = userClient.wechatBinding(openId, unionId, AuthManager.currentUser().getId(), wechatIdDto.getAvatar(), wechatIdDto.getNickName());

        if (userDto == null) {
            throw new BusinessException(BusinessExceptionEnum.WECHAT_BINDING_FAIL.getCode(), BusinessExceptionEnum.WECHAT_BINDING_FAIL.getMessage());
        } else {
            return userDto;
        }
    }

    @Override
    public Map<String, Object> wechatLogin(WechatIdDto wechatIdDto) {
        UserDto userDto = userClient.wechatLogin(wechatIdDto.getOpenId(), wechatIdDto.getUnionId(), wechatIdDto.getAvatar(), wechatIdDto.getNickName());
        if (userDto == null) {
            throw new BusinessException(BusinessExceptionEnum.WECHAT_LOGIN_FAIL.getCode(), BusinessExceptionEnum.WECHAT_LOGIN_FAIL.getMessage());
        }
        return getLoginResultMap(userDto);
    }

    private HashMap<String, Object> getLoginResultMap(UserDto userDto) {
        JWTUserDto jwtUserDto = new JWTUserDto();
        jwtUserDto.setId(userDto.getId());
        jwtUserDto.setUserId(userDto.getId());
        jwtUserDto.setAccountId(userDto.getAccount());
        HashMap<String, Object> resultMap = Maps.newHashMap();
        List<MemberDto> memberLevel = memberClient.getMemberLevel(new String[]{userDto.getId()});
        userDto.setAgencyLevel(memberLevel.get(0).getAgencyLevel());
        resultMap.put(USER, userDto);
        resultMap.put(TOKEN, jwtTokenContext.generateToken(jwtUserDto));


        return resultMap;
    }

    @Override
    public List<AddressDto> getAddressList() {
        JWTUserDto userDto = AuthManager.currentUser();
        return userClient.getAddressList(userDto.getId());
    }

    @Override
    public AddressDto updateAddress(AddressDto addressDto) {
        JWTUserDto userDto = AuthManager.currentUser();
        return userClient.updateAddress(userDto.getId(), addressDto);
    }

    @Override
    public void deleteAddress(String addressId) {
        JWTUserDto userDto = AuthManager.currentUser();
        userClient.deleteAddress(userDto.getId(), addressId);
    }

    @Override
    public AddressDto addAddress(AddressDto addressDto) {
        JWTUserDto userDto = AuthManager.currentUser();
        return userClient.addAddress(userDto.getId(), addressDto);
    }

    @Override
    public void setDefaultAddress(String addressId) {
        JWTUserDto userDto = AuthManager.currentUser();
        userClient.setDefaultAddress(userDto.getId(), addressId);
    }

    @Override
    public void activationAccount(String code) {
        ActivationDto activationDto = new ActivationDto();
        activationDto.setCode(code);
        activationDto.setPeopleId(AuthManager.currentUser().getId());
        userClient.activationAccount(activationDto);
    }

    @Override
    public UserDto updateBodyInfo(BodyInfoDto bodyInfoDto) {
        UserDto userDto = new UserDto();
        JWTUserDto jwtUser = AuthManager.currentUser();
        userDto.setId(jwtUser.getId());
        userDto.setBodyInfo(bodyInfoDto);
        return userClient.updateUserInfo(userDto);
    }


    @Override
    public UserDto updateUserAvatar(AvatarDto avatarDto) {
        JWTUserDto jwtUser = AuthManager.currentUser();
        String userId = jwtUser.getId();
        UserDto userDto = new UserDto();
        userDto.setId(userId);
        userDto.setAvatar(avatarDto.getAvatar());
        sendFileMessage(avatarDto, userId);
        return userClient.updateUserInfo(userDto);
    }

    @Override
    public CodeConfirmDto getInviteCodeUserName(String code) {
        return new CodeConfirmDto(userClient.getInviteCodeUserName(code));
    }

    @Override
    public UserDtoForSupperVIP getUserInfoSupper() {
        JWTUserDto jwtUser = AuthManager.currentUser();
        return userClient.getUserInfoByuserIdForSupperVip(jwtUser.getId());
    }

    @Override
    public String getInivterPhone() {
        JWTUserDto jwtUser = AuthManager.currentUser();
        InviterDto inviterDto = userClient.getInviterUserId(jwtUser.getUserId());
        String phone=null;
        if(inviterDto!=null){
             phone = inviterDto.getPhone();
        }
        return phone;
    }

    @Override
    public UserDto getUserInfoByInviteCode(String code) {
        return userClient.getUserInfoByInviteCode(code);
    }

    @Override
    public InviterDto getInviterInfo() {
        JWTUserDto jwtUser = AuthManager.currentUser();
        InviterDto inviter = userClient.getInviterUserId(jwtUser.getUserId());
        return inviter;
    }

    private void sendFileMessage(AvatarDto avatarDto, String userId) {
        AppendFileUseRecordDto appendFileUseRecordDto = new AppendFileUseRecordDto();
        appendFileUseRecordDto.setFileId(new String[]{avatarDto.getFileId()});
        appendFileUseRecordDto.setBizId(userId);
        appendFileUseRecordDto.setBizPath("/microservice-user/user/updateAvatar");
        EventBus.publish(AppendFileUseRecordDto.APPEND, appendFileUseRecordDto);
    }

    @Override
    public UserDto updateUserInfo(UserDto userDto) {
        JWTUserDto jwtUser = AuthManager.currentUser();
        userDto.setId(jwtUser.getId());
        return userClient.updateUserInfo(userDto);
    }

    @Override
    public Map<String, Object> getCenterInfo() {
        String peopleId = AuthManager.currentUser().getId();
        UserSimpleDto userSimpleDto = userClient.simpleInfo(peopleId);

        HashMap<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("user", userSimpleDto);
        return resultMap;
    }

    @Override
    public Map<String, Object> getUserInfo() {
        UserDto userDto = userClient.getUserInfo(AuthManager.currentUser().getId());

        return getLoginResultMap(userDto);
    }
}
