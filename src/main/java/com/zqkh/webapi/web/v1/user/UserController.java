package com.zqkh.webapi.web.v1.user;

import com.jovezhao.nest.utils.JsonUtils;
import com.zqkh.user.feign.dto.*;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.domain.dto.user.IdCardValidateDto;
import com.zqkh.webapi.context.domain.vo.user.IdCardValidateVo;
import com.zqkh.webapi.context.dto.user.*;
import com.zqkh.webapi.context.dto.user.RealNameAuthDto;
import com.zqkh.webapi.context.service.RealNameAuthService;
import com.zqkh.webapi.context.service.UserService;
import com.zqkh.webapi.context.verifyidcard.RealNameAuth;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @author wenjie
 * @date 2017/12/11 0011 11:49
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    RealNameAuthService realNameAuthService;

    /**
     * 验证码登陆
     *
     * @param dto
     * @return
     */
    @Anonymous
    @PostMapping("/captchaLogin")
    @ApiOperation(value = "api_user_captchaLogin")
    public Map captchaLogin(@RequestBody @Valid CaptchaLoginDto dto) {
        return userService.captchaLoginByMySelf(dto);
    }

    @GetMapping("/center")
    @ApiOperation(value = "api_user_center")
    public Map center() {
        return userService.getCenterInfo();
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/getUserInfo")
    @ApiOperation(value = "api_user_getInfo")
    public Map getUserInfo() {
        return userService.getUserInfo();
    }

    /**
     * 邀请码用户提示
     *
     * @return
     */
    @Anonymous
    @GetMapping("/code/confirm")
    @ApiOperation(value = "api_user_code_confirm")
    public CodeConfirmDto codeConfirm(@NotNull @Pattern(regexp = "^\\w{6}$", message = "激活码格式错误") String code) {
        return userService.getInviteCodeUserName(code);
    }

    @Anonymous
    @ApiOperation(value = "getUserInfoByInviteCode")
    @GetMapping("/user/getUserInfoByInviteCode")
    UserDto getUserInfoByInviteCode(@RequestParam("code") String code){
        return userService.getUserInfoByInviteCode(code);
    }

    @PostMapping("/activationAccount")
    @ApiOperation(value = "api_user_activation")
    public void activationAccount(@RequestBody @NotNull @Pattern(regexp = "^\\w{6}$", message = "激活码格式错误") String code) {
        userService.activationAccount(code);
    }

    @ApiOperation(value = "api_user_getAddress")
    @GetMapping("/getAddressList")
    public List<AddressDto> getAddressList() {
        return userService.getAddressList();
    }

    @ApiOperation(value = "api_user_updateAddress")
    @PostMapping("/updateAddress")
    public AddressDto updateAddress(@RequestBody @Valid AddressDto addressDto) {
        return userService.updateAddress(addressDto);
    }

    @ApiOperation(value = "api_user_deleteAddress")
    @PostMapping("/deleteAddress")
    public void deleteAddress(@RequestBody String addressId) {
        userService.deleteAddress(addressId);
    }

    @ApiOperation(value = "api_user_addAddress")
    @PostMapping("/addAddress")
    public AddressDto addAddress(@RequestBody @Valid AddressDto addressDto) {
        return userService.addAddress(addressDto);
    }

    @ApiOperation(value = "api_user_setDefaultAddress")
    @PostMapping("/setDefaultAddress")
    public void setDefaultAddress(@RequestBody String addressId) {
        userService.setDefaultAddress(addressId);
    }

    /**
     * 微信登陆
     *
     * @param wechatIdDto
     * @return
     */
    @ApiOperation(value = "api_user_wechatLogin")
    @Anonymous
    @PostMapping("/wechatLogin")
    public Map wechatLogin(@RequestBody WechatIdDto wechatIdDto) {
        return userService.wechatLogin(wechatIdDto);
    }

    /**
     * 微信绑定登录
     *
     * @param wechatLoginDto
     * @return
     */
    @ApiOperation(value = "api_user_wechatBindingLogin")
    @Anonymous
    @PostMapping("/wechatBindingLogin")
    public Map wechatBinding(@RequestBody @Valid WechatLoginDto wechatLoginDto) {
        return userService.wechatBindingLogin(wechatLoginDto);
    }

    /**
     * 微信绑定
     *
     * @param wechatIdDto
     * @return
     */
    @ApiOperation(value = "api_user_wechatBinding")
    @PostMapping("/wechatBinding")
    public UserDto wechat(@RequestBody WechatIdDto wechatIdDto) {
        return userService.wechatBinding(wechatIdDto);
    }

    /**
     * 用户实名认证
     *
     * @param realNameAuthDto
     * @return
     */
    @ApiOperation(value = "api_user_userCertification")
    @PostMapping("/userCertification")
    public Map userCertification(@RequestBody @Valid RealNameAuthDto realNameAuthDto) throws UnsupportedEncodingException {

        return JsonUtils.toObj(realNameAuthService.checkRealName(new RealNameAuth(realNameAuthDto.getName(), realNameAuthDto.getIdCard(), realNameAuthDto.getCardFront(), realNameAuthDto.getCardBack())), Map.class);

    }


    /**
     * 身份证验证
     *
     * @param idCardValidateVo
     * @return
     */
    @ApiOperation(value = "api_user_idCardValidate")
    @PostMapping("/idCardValidate")
    public IdCardValidateDto idCardValidate(@RequestBody IdCardValidateVo idCardValidateVo) {
        return realNameAuthService.idCardValidate(idCardValidateVo);
    }


    /**
     * 编辑头像信息
     *
     * @param avatarDto
     * @return
     */
    @ApiOperation(value = "api_user_updateAvatar")
    @PostMapping("/updateAvatar")
    UserDto updateAvatar(@RequestBody @Valid AvatarDto avatarDto) {
        return userService.updateUserAvatar(avatarDto);
    }

    /**
     * 编辑性别
     *
     * @param userDto
     * @return
     */
    @ApiOperation(value = "api_user_updateSex")
    @PostMapping("/updateSex")
    UserDto updateSex(@RequestBody UserDto userDto) {
        return userService.updateUserInfo(userDto);
    }

    /**
     * 编辑昵称
     *
     * @param userDto
     * @return
     */
    @ApiOperation(value = "api_user_updateNickName")
    @PostMapping("/updateNickName")
    UserDto updateNickName(@RequestBody UserDto userDto) {
        return userService.updateUserInfo(userDto);
    }

    /**
     * 编辑民族
     *
     * @param userDto
     * @return
     */
    @ApiOperation(value = "api_user_updateEthnic")
    @PostMapping("/updateEthnic")
    UserDto updateEthnic(@RequestBody UserDto userDto) {
        return userService.updateUserInfo(userDto);
    }

    /**
     * 编辑籍贯
     *
     * @param userDto
     * @return
     */
    @ApiOperation(value = "api_user_updateNativePlace")
    @PostMapping("/updateNativePlace")
    UserDto updateNativePlace(@RequestBody UserDto userDto) {
        return userService.updateUserInfo(userDto);
    }

    /**
     * 编辑职业
     *
     * @param userDto
     * @return
     */
    @ApiOperation(value = "api_user_updateProfession")
    @PostMapping("/updateProfession")
    UserDto updateProfession(@RequestBody UserDto userDto) {
        return userService.updateUserInfo(userDto);
    }

    /**
     * 编辑血型
     *
     * @param userDto
     * @return
     */
    @ApiOperation(value = "api_user_updateBloodType")
    @PostMapping("/updateBloodType")
    UserDto updateBloodType(@RequestBody UserDto userDto) {
        return userService.updateUserInfo(userDto);
    }

    /**
     * 编辑体征信息
     *
     * @param bodyInfoDto
     * @return
     */
    @PostMapping("/updateBodyInfo")
    @ApiOperation(value = "api_user_updateBodyInfo")
    public UserDto updateBodyInfo(@RequestBody BodyInfoDto bodyInfoDto) {
        return userService.updateBodyInfo(bodyInfoDto);
    }

    /**
     * supperVip banner
     *
     * @return
     */
    @GetMapping("/getUserInfoSupper")
    @ApiOperation(value = "api_user_getUserInfoSupper")
    public UserDtoForSupperVIP getUserInfoSupper() {
        return userService.getUserInfoSupper();
    }


    /**
     * 获取推荐人的电话号码
     *
     * @return
     */
    @GetMapping("/getInivterPhone")
    @ApiOperation(value = "api_user_getInivterPhone")
    public String getInivterPhone() {
        return userService.getInivterPhone();
    }

    /**
     * 获取推荐人的信息
     */
    @GetMapping("/getInviterInfo")
    @ApiOperation(value = "api_user_getInviterInfo")
    public InviterDto getInviterInfo() {
        return userService.getInviterInfo();
    }
}
