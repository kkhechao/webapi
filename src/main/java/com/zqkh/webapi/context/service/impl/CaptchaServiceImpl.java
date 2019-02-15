package com.zqkh.webapi.context.service.impl;

import com.jovezhao.nest.ddd.event.EventBus;
import com.jovezhao.nest.starter.AppService;
import com.zqkh.sms.event.dto.SMSVerifyCodeDTO;
import com.zqkh.webapi.context.constant.Constants;
import com.zqkh.webapi.context.dto.system.CaptchaDto;
import com.zqkh.webapi.context.service.CaptchaService;
import com.zqkh.webapi.context.utils.RedisCacheUtil;
import com.zqkh.webapi.context.utils.UtilTools;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @author wenjie
 * @date 2017/12/8 0008 17:06
 */
@AppService
public class CaptchaServiceImpl implements CaptchaService {

    //验证码长度
    public static final int CAPTCHA_LENGTH = 4;
    //验证码过期时间
    private static final int CAPTCHA_TIMEOUT = 10;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Override
    public void sendCaptcha(CaptchaDto captchaDto) {
        String phone = captchaDto.getPhone();
        String captcha = UtilTools.getCaptcha(CAPTCHA_LENGTH);
        redisCacheUtil.setCacheObject(captchaDto.getPhone(), captcha, CAPTCHA_TIMEOUT, TimeUnit.MINUTES);
        SMSVerifyCodeDTO smsVerifyCodeDTO = new SMSVerifyCodeDTO(phone, captcha, Constants.System.MICROSERVICE_NAME,SMSVerifyCodeDTO.Format.valueOf(captchaDto.getFormat().toString()),SMSVerifyCodeDTO.Type.valueOf(captchaDto.getType().toString()));
        EventBus.publish(SMSVerifyCodeDTO.VERIFY_CODE_EVENT_NAME, smsVerifyCodeDTO);
    }
}
