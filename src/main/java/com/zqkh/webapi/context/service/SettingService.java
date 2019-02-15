package com.zqkh.webapi.context.service;

import com.zqkh.webapi.context.dto.system.SettingDto;

import java.util.List;

/**
 * 系统设置
 *
 * @author 悭梵
 * @date Created in 2018-06-07 9:53
 */
public interface SettingService {
    /**
     *
     * @return
     */
    List<SettingDto> listSystemSetting();
}
