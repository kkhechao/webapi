package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.domain.dto.healthhistory.diet.ApiDietHistoryDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.diet.ApiDietHistoryPageVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.diet.ApiDietHistoryVo;

public interface DietHistoryService {

    /**
     * 添加记录
     */
    void addDietHistory(ApiDietHistoryVo inVo);

    /**
     * 获取分页信息
     */
    PageResult<ApiDietHistoryDto> getDietHistories(ApiDietHistoryPageVo inVo);

    /**
     * 删除记录信息
     */
    void deleteDietHistory(String id);

}
