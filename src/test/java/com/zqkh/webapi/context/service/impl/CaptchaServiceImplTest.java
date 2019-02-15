package com.zqkh.webapi.context.service.impl;

import com.zqkh.webapi.Application;
import com.zqkh.webapi.context.dto.system.CaptchaDto;
import com.zqkh.webapi.context.service.CaptchaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wenjie
 * @date 2017/12/11 0011 10:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CaptchaServiceImplTest {

    @Autowired
    private CaptchaService captchaService;

    @Test
    public void sendCaptcha() throws Exception {
        CaptchaDto dto = new CaptchaDto();
        dto.setPhone("13688031263");
        dto.setType(CaptchaDto.Type.LOGIN);
        captchaService.sendCaptcha(dto);
    }

}