package com.zqkh.webapi.web.v1.system;

import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.dto.system.SettingDto;
import com.zqkh.webapi.context.service.SettingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统设置
 *
 * @author 悭梵
 * @date Created in 2018-06-07 9:53
 */
@RestController
public class SettingController {

    @Resource
    private SettingService settingService;

    @Anonymous
    @GetMapping("/system/setting")
    @ApiOperation(value = "api_setting_system", notes = "系统设置")
    public List<SettingDto> systemSetting() {
        return settingService.listSystemSetting();
    }
}
