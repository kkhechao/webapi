package com.zqkh.webapi.context.service;

import com.zqkh.healthy.feign.dto.bracelet.app.ReportUnits;
import com.zqkh.webapi.context.domain.vo.bracelet.RespiratoryRateVo;
import com.zqkh.webapi.context.dto.healthy.bracelet.ReportResultDto;

import java.util.List;

public interface RespiratoryRateService {

    void save(RespiratoryRateVo vo);

    List<ReportResultDto> report(String userId, String familyMemberId, ReportUnits reportUnits);
}
