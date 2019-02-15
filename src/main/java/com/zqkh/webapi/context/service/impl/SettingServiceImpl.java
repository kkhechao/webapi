package com.zqkh.webapi.context.service.impl;

import com.zqkh.system.feign.SettingFeignClient;
import com.zqkh.webapi.context.dto.system.SettingDto;
import com.zqkh.webapi.context.service.SettingService;
import com.zqkh.wemedia.feign.CommentFeignClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统设置
 *
 * @author 悭梵
 * @date Created in 2018-06-07 10:02
 */
@Service
public class SettingServiceImpl implements SettingService {

    /**
     * 总开关，用于控制前端是否现在评论，默认显示
     */
    private static final String WEMEDIA_SHOW_COMMENT = "WEMEDIA_SHOW_COMMENT";
    /**
     * 总开关，用于控制是否允许评论，默认允许
     */
    private static final String WEMEDIA_ALLOW_COMMENT = "WEMEDIA_ALLOW_COMMENT";
    /**
     * 总开关，用于控制评论是否需输入验证码，默认不需要
     */
    private static final String WEMEDIA_COMMENT_NEED_CAPTCHA = "WEMEDIA_COMMENT_NEED_CAPTCHA";

    @Resource
    private SettingFeignClient settingFeignClient;

    @Override
    public List<SettingDto> listSystemSetting() {
        List<com.zqkh.system.feign.dto.SettingDto> list = new ArrayList<>(5);
        String appId = CommentFeignClient.class.getAnnotation(FeignClient.class).value();
        list.add(settingFeignClient.info(appId, WEMEDIA_SHOW_COMMENT));
        list.add(settingFeignClient.info(appId, WEMEDIA_ALLOW_COMMENT));
        list.add(settingFeignClient.info(appId, WEMEDIA_COMMENT_NEED_CAPTCHA));
        return list.stream().map(feignSettingDto -> {
            if (feignSettingDto == null) {
                return null;
            }
            SettingDto settingDto = new SettingDto();
            settingDto.setId(feignSettingDto.getId());
            settingDto.setKey(feignSettingDto.getKey());
            settingDto.setName(feignSettingDto.getName());
            settingDto.setAppId(feignSettingDto.getAppId());
            settingDto.setValue(feignSettingDto.getValue());
            settingDto.setRemark(feignSettingDto.getRemark());
            settingDto.setCreateTime(feignSettingDto.getCreateTime());
            return settingDto;
        }).filter(o -> o != null).collect(Collectors.toList());
    }
}
