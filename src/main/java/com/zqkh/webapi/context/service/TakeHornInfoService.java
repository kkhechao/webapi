package com.zqkh.webapi.context.service;


import com.zqkh.webapi.context.domain.dto.healthhistory.takehorn.ApiTakeHornInfoDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.takehorn.ApiTakeHornInfoListVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.takehorn.ApiTakeHornInfoVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 饮酒信息 Service
 */
public interface TakeHornInfoService {

    List<ApiTakeHornInfoDto> getTakeHornInfo(@RequestBody ApiTakeHornInfoListVo inVo);

    void addTakeHornInfo(@RequestBody ApiTakeHornInfoVo inVo);

    void deleteTakeHornInfo(@RequestParam("id") String id);

}
