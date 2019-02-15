package com.zqkh.webapi.context.service;


import com.zqkh.healthy.feign.dto.bracelet.app.ReportUnits;
import com.zqkh.webapi.context.domain.vo.bracelet.HrrestVo;
import com.zqkh.webapi.context.dto.healthy.bracelet.ReportResultDto;

import java.util.List;

/**
 * 心率
 */
public interface HrrestService {

    public void save(HrrestVo hrrestVo);

    List<ReportResultDto> report(String userId, String familyMemberId, ReportUnits reportUnits);
}
