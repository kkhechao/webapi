package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.domain.dto.healthhistory.irritability.ApiIrritabilityHistoryDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.irritability.ApiIrritabilityHistoryPageVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.irritability.ApiIrritabilityHistoryVo;

/**
 * 过敏史 Service
 */
public interface IrritabilityHistoryService {

    /**
     * 获取分页信息
     */
    PageResult<ApiIrritabilityHistoryDto> getIrritabilityHistories(ApiIrritabilityHistoryPageVo inVo);

    /**
     * 添加记录
     */
    void addIrritabilityHistory(ApiIrritabilityHistoryVo inVo);

    /**
     * 修改记录
     */
    void editIrritabilityHistory(ApiIrritabilityHistoryVo inVo);

    /**
     * 删除记录
     */
    void deleteIrritabilityHistory(String id);

}
