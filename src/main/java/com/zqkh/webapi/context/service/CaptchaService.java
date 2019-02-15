package com.zqkh.webapi.context.service;

import com.zqkh.webapi.context.dto.system.CaptchaDto;
/**
 * @author wenjie
 * @date 2017/12/8 0008 17:06
 */
public interface CaptchaService {
    void sendCaptcha(CaptchaDto captchaDto);
}
