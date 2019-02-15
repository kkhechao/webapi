package com.zqkh.webapi.context.service;

import com.zqkh.webapi.context.domain.dto.healthhistory.takehorn.ApiTakeHornDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.takehorn.ApiTakeHornHistoryInfoVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.takehorn.ApiTakeHornVo;

/**
 * 饮酒史 Service
 */
public interface TakeHornService {

    /**
     * 添加记录
     */
    void addTakeHorn(ApiTakeHornVo inVo);

    /**
     * 修改记录
     */
    void editTakeHorn(ApiTakeHornVo inVo);

    /**
     * 删除记录
     */
    void deleteTakeHorn(String id);

    /**
     * 获取饮酒信息
     */
    ApiTakeHornDto getTakeHorn(ApiTakeHornHistoryInfoVo inVo);

}
