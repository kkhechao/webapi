package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.domain.dto.healthhistory.genetic.ApiGeneticHistoryDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.genetic.ApiGeneticHistoryPageVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.genetic.ApiGeneticHistoryVo;

/**
 * 家族病史 Service
 */
public interface GeneticHistoryService {

    /**
     * 添加记录
     */
    void addGeneticHistory(ApiGeneticHistoryVo inVo);

    /**
     * 修改记录
     */
    void editGeneticHistory(ApiGeneticHistoryVo inVo);

    /**
     * 删除记录
     */
    void deleteGeneticHistory(String id);


    /**
     * 获取分页信息
     */
    PageResult<ApiGeneticHistoryDto> getGeneticHistories(ApiGeneticHistoryPageVo inVo);

}
