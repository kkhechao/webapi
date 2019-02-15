package com.zqkh.webapi.context.service.impl;

import com.jovezhao.nest.utils.JsonUtils;
import com.zqkh.user.feign.UserClient;
import com.zqkh.user.feign.dto.RealNameAuthDto;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.domain.dto.user.IdCardValidateDto;
import com.zqkh.webapi.context.domain.vo.user.IdCardValidateVo;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.RealNameAuthService;
import com.zqkh.webapi.context.verifyidcard.RealNameAuth;
import com.zqkh.webapi.context.verifyidcard.VerificationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Service
public class RealNameAuthServiceImpl implements RealNameAuthService {

    //匹配
    private static final String SUCCEED = "0.0";

    //不匹配
    private static final String MISMATCH = "5.0";

    //无此身份证号
    private static final String NOTHING_IDCARD = "14.0";

    @Autowired
    private VerificationContext verificationContext;

    @Autowired
    private UserClient userClient;

    @Override
    public String checkRealName(RealNameAuth realNameAuth) throws UnsupportedEncodingException {

        JWTUserDto userDto = AuthManager.currentUser();

        String responseBody = verificationContext.authenticationRealName(realNameAuth);
        Map map = JsonUtils.toObj(responseBody, Map.class);
        Map<String, Object> respMap = (Map<String, Object>) map.get("resp");
        if (SUCCEED.equals(String.valueOf(respMap.get("code")))) {
            RealNameAuthDto dto = new RealNameAuthDto();
            dto.setName(realNameAuth.getName());
            dto.setIdCard(realNameAuth.getIdCard());
            dto.setCardFront(realNameAuth.getCardFront());
            dto.setCardBack(realNameAuth.getCardBack());
            userClient.addAuthInfo(userDto.getId(), dto);
        }
        return responseBody;
    }

    @Override
    public IdCardValidateDto idCardValidate(IdCardValidateVo idCardValidateVo) {
        if (ObjectUtils.isEmpty(idCardValidateVo)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "身份证信息为空");
        }
        if (ObjectUtils.isEmpty(idCardValidateVo.getIdCard())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "身份证为空");
        }
        if (ObjectUtils.isEmpty(idCardValidateVo.getName())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "姓名为空");
        }
        RealNameAuth realNameAuth = new RealNameAuth(idCardValidateVo.getName(), idCardValidateVo.getIdCard(), null, null);

        try {
            String responseBody = verificationContext.authenticationRealName(realNameAuth);

            Map map = JsonUtils.toObj(responseBody, Map.class);
            Map<String, Object> respMap = (Map<String, Object>) map.get("resp");


            String code = String.valueOf(respMap.get("code"));

            switch (code) {
                case SUCCEED:
                    return IdCardValidateDto.builder().validate(true).msg("匹配").build();
                case MISMATCH:
                    return IdCardValidateDto.builder().validate(false).msg("身份证不匹配").build();
                case NOTHING_IDCARD:
                    return IdCardValidateDto.builder().validate(false).msg("无此身份证").build();
                default:
                    return IdCardValidateDto.builder().validate(false).msg("认证失败").build();
            }
        } catch (IOException e) {
            throw new BusinessException(BusinessExceptionEnum.IO_EXCEPTION.getCode(), "校验异常,请稍后");
        }
    }
}
