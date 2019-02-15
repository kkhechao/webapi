package com.zqkh.webapi.context.service;


import com.zqkh.webapi.context.domain.dto.healthhistory.smoking.ApiSmokingInfoDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.smoking.ApiSmokingInfoListVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.smoking.ApiSmokingInfoVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 吸烟信息 Service
 */
public interface SmokingInfoService {

    List<ApiSmokingInfoDto> getSmokingInfo(@RequestBody ApiSmokingInfoListVo inVo);

    void addSmokingInfo(@RequestBody ApiSmokingInfoVo inVo);

    void deleteSmokingInfo(@RequestParam("id") String id);

}
