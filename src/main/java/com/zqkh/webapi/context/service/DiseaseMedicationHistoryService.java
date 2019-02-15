package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.domain.dto.healthhistory.disease.ApiDiseaseMedicationHistoryDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.disease.ApiDiseaseMedicationHistoryPageVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.disease.ApiDiseaseMedicationHistoryVo;

public interface DiseaseMedicationHistoryService {

    /**
     * 添加记录
     */
    void addDiseaseMedicationHistory(ApiDiseaseMedicationHistoryVo inVo);

    /**
     * 获取分页信息
     */
    PageResult<ApiDiseaseMedicationHistoryDto> getDiseaseMedicationHistories(ApiDiseaseMedicationHistoryPageVo inVo);

    /**
     * 修改记录信息
     */
    void editDiseaseMedicationHistory(ApiDiseaseMedicationHistoryVo inVo);

    /**
     * 修改用药史信息
     */
    void editMedicationHistories(ApiDiseaseMedicationHistoryVo inVo);

    /**
     * 删除记录信息
     */
    void deleteDiseaseMedicationHistory(String id);

}



