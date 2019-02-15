package com.zqkh.webapi.context.service.impl;

import com.jovezhao.nest.ddd.event.EventBus;
import com.jovezhao.nest.starter.AppService;
import com.zqkh.common.exception.BusinessException;
import com.zqkh.healthy.event.dto.RespiratoryRateDto;
import com.zqkh.healthy.feign.RespiratoryRateClient;
import com.zqkh.healthy.feign.dto.bracelet.app.ReportDto;
import com.zqkh.healthy.feign.dto.bracelet.app.ReportUnits;
import com.zqkh.webapi.context.domain.vo.bracelet.RespiratoryRateVo;
import com.zqkh.webapi.context.dto.healthy.bracelet.ReportResultDto;
import com.zqkh.webapi.context.service.RespiratoryRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangchao
 * @time: 2018-07-18 18:59
 **/
@AppService
public class RespiratoryRateServiceImpl implements RespiratoryRateService {

    @Autowired
    RespiratoryRateClient respiratoryRateClient;


    @Override
    public void save(RespiratoryRateVo vo) {
        if(ObjectUtils.isEmpty(vo)){
            throw new BusinessException("对象为空");
        }
        if(ObjectUtils.isEmpty(vo.getDate())){
            throw new BusinessException("日期为空");
        }
        if(ObjectUtils.isEmpty(vo.getDeviceId())){
            throw new BusinessException("设备编号为空");
        }
        if(ObjectUtils.isEmpty(vo.getStartTime())){
            throw new BusinessException("开始时间为空");
        }
        if(ObjectUtils.isEmpty(vo.getEndTime())){
            throw new BusinessException("结束时间为空");
        }
        if(ObjectUtils.isEmpty(vo.getFamilyMemberId())){
            throw new BusinessException("家庭成员为空");
        }
        if(ObjectUtils.isEmpty(vo.getRespiratoryRate())){
            throw new BusinessException("呼吸频率为空");
        }
        if(ObjectUtils.isEmpty(vo.getUserId())){
            throw new BusinessException("用户为空");
        }

        RespiratoryRateDto dto = new RespiratoryRateDto();
        dto.setDate(vo.getDate());
        dto.setDeviceId(vo.getDeviceId());
        dto.setEndTime(vo.getEndTime());
        dto.setFamilyMemberId(vo.getFamilyMemberId());
        dto.setRespiratoryRate(vo.getRespiratoryRate());
        dto.setStartTime(vo.getStartTime());
        dto.setUserId(vo.getUserId());
        EventBus.publish(RespiratoryRateDto.EVENT_NAME,dto);

    }


    @Override
    public List<ReportResultDto> report(String userId, String familyMemberId, ReportUnits reportUnits) {
        if(ObjectUtils.isEmpty(userId)){
            throw new BusinessException("用户为空");
        }
        if(ObjectUtils.isEmpty(familyMemberId)){
            throw new BusinessException("家庭成员为空");
        }
        if(ObjectUtils.isEmpty(reportUnits)){
            throw new BusinessException("查询单位为空");
        }
        List<ReportDto> report = respiratoryRateClient.report(familyMemberId,userId , reportUnits);
        List<ReportResultDto> dto = new ArrayList<>();
        report.forEach(item -> {
            ReportResultDto reportResultDto = new ReportResultDto();
            reportResultDto.setDate(item.getDate());
            reportResultDto.setValue(item.getValue());
            dto.add(reportResultDto);
        });
        return dto;
    }
}
