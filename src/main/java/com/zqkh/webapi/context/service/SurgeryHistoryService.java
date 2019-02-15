package com.zqkh.webapi.context.service;


import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.domain.dto.healthhistory.surgery.ApiSurgeryHistoryDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.surgery.ApiSurgeryHistoryPageVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.surgery.ApiSurgeryHistoryVo;

/**
 * 手术史 Service
 */
public interface SurgeryHistoryService {

    /**
     * 添加记录
     */
    void addSurgeryHistory(ApiSurgeryHistoryVo inVo);

    /**
     * 修改记录
     */
    void editSurgeryHistory(ApiSurgeryHistoryVo inVo);

    /**
     * 删除记录
     */
    void deleteSurgeryHistory(String id);


    /**
     * 获取分页信息
     */
    PageResult<ApiSurgeryHistoryDto> getSurgeryHistories(ApiSurgeryHistoryPageVo inVo);

}
