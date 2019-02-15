package com.zqkh.webapi.web.v1.system;

import com.jovezhao.nest.utils.JsonUtils;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.configurtion.properties.CloudConfigProperties;
import com.zqkh.webapi.context.dto.system.AreaCodeDto;
import com.zqkh.webapi.context.dto.system.CaptchaDto;
import com.zqkh.webapi.context.dto.system.VersionDto;
import com.zqkh.webapi.context.service.CaptchaService;
import com.zqkh.webapi.context.service.SystemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/system")
public class SystemController {

    public static final String Android = "Android";
    public static final String IOS = "iOS";


    @Autowired
    private CloudConfigProperties cloudConfigProperties;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private SystemService systemService;

    @Anonymous
    @GetMapping("/sendCaptcha")
    @ApiOperation(value = "api_system_sendCaptcha")
    public void getCaptcha(@Valid CaptchaDto captchaDto) {
        captchaService.sendCaptcha(captchaDto);
    }

    /**
     * 获取版本信息
     */
    @GetMapping("/getVersionInfo")
    @Anonymous
    @ApiOperation(value = "api_system_getVersionInfo", notes = "获取版本信息")
    public VersionDto getVersionInfo(String deviceType) {
        VersionDto versionDto = new VersionDto();
        CloudConfigProperties.AndroidVersion androidVersion = cloudConfigProperties.getAndroidVersion();
        CloudConfigProperties.IosVersion iosVersion = cloudConfigProperties.getIosVersion();
        if (Android.equals(deviceType)) {
            return new VersionDto(androidVersion.getIntro(),androidVersion.getLatestVersion(),
                    androidVersion.getMandatoryUpdate(),androidVersion.getVersionCode(),
                    androidVersion.getVersionName(),androidVersion.getUrl());
        } else if (IOS.equals(deviceType)) {
            return new VersionDto(iosVersion.getIntro(),iosVersion.getLatestVersion(),
                    iosVersion.getMandatoryUpdate(),iosVersion.getVersionCode(),
                    iosVersion.getVersionName(),iosVersion.getUrl());
        }
        return versionDto;
    }

    @Anonymous
    @GetMapping("/getAreaData")
    @ApiOperation(value = "api_system_getAreaData")
    public List getAreaCodeList() {
        List<AreaCodeDto> areaCodeDtoList = JsonUtils.toObj(systemService.getAreaCodeList(), List.class);
        return areaCodeDtoList;
    }
}
