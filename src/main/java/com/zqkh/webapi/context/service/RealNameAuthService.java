package com.zqkh.webapi.context.service;

import com.zqkh.webapi.context.domain.dto.user.IdCardValidateDto;
import com.zqkh.webapi.context.domain.vo.user.IdCardValidateVo;
import com.zqkh.webapi.context.verifyidcard.RealNameAuth;

import java.io.UnsupportedEncodingException;

/**
 * 实名认证服务接口
 */
public interface RealNameAuthService {

    String checkRealName(RealNameAuth realNameAuth) throws UnsupportedEncodingException;


    /**
     * 身份证验证
     *
     * @param idCardValidateVo
     * @return
     */
    IdCardValidateDto idCardValidate(IdCardValidateVo idCardValidateVo);
}
