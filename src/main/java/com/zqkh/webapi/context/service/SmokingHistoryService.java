package com.zqkh.webapi.context.service;

import com.zqkh.webapi.context.domain.dto.healthhistory.smoking.ApiSmokingHistoryDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.smoking.ApiSmokingHistoryInfoVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.smoking.ApiSmokingHistoryVo;

/**
 * 吸烟史 Service
 */
public interface SmokingHistoryService {

    /**
     * 添加记录
     */
    void addSmokingHistory(ApiSmokingHistoryVo inVo);

    /**
     * 修改记录
     */
    void editSmokingHistory(ApiSmokingHistoryVo inVo);

    /**
     * 删除记录
     */
    void deleteSmokingHistory(String id);

    /**
     * 获取吸烟信息
     */
    ApiSmokingHistoryDto getSmokingHistory(ApiSmokingHistoryInfoVo inVo);

}
