package com.zqkh.webapi.context.service.impl;

import com.jovezhao.nest.ddd.event.EventBus;
import com.jovezhao.nest.starter.AppService;
import com.zqkh.common.exception.BusinessException;
import com.zqkh.healthy.event.dto.HrrestDto;
import com.zqkh.healthy.feign.HrrestClient;
import com.zqkh.healthy.feign.dto.bracelet.app.ReportDto;
import com.zqkh.healthy.feign.dto.bracelet.app.ReportUnits;
import com.zqkh.webapi.context.domain.vo.bracelet.HrrestVo;
import com.zqkh.webapi.context.dto.healthy.bracelet.ReportResultDto;
import com.zqkh.webapi.context.service.HrrestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 心率
 *
 * @author: zhangchao
 * @time: 2018-07-18 15:51
 **/
@AppService
public class HrrestServiceImpl implements HrrestService {

    @Autowired
    HrrestClient hrrestClient;

    @Override
    public void save(HrrestVo hrrestVo) {

        if(ObjectUtils.isEmpty(hrrestVo)){
            throw new BusinessException("对象为空");
        }
        if(ObjectUtils.isEmpty(hrrestVo.getDate())){
            throw new BusinessException("日期为空");
        }
        if(ObjectUtils.isEmpty(hrrestVo.getDeviceId())){
            throw new BusinessException("设备编号为空");
        }
        if(ObjectUtils.isEmpty(hrrestVo.getStartTime())){
            throw new BusinessException("开始时间为空");
        }
        if(ObjectUtils.isEmpty(hrrestVo.getEndTime())){
            throw new BusinessException("结束时间为空");
        }
        if(ObjectUtils.isEmpty(hrrestVo.getFamilyMemberId())){
            throw new BusinessException("家庭成员为空");
        }
        if(ObjectUtils.isEmpty(hrrestVo.getHrrest())){
            throw new BusinessException("心率为空");
        }
        if(ObjectUtils.isEmpty(hrrestVo.getUserId())){
            throw new BusinessException("用户为空");
        }
        HrrestDto hrrestDto = new HrrestDto();
        hrrestDto.setDate(hrrestVo.getDate());
        hrrestDto.setDeviceId(hrrestVo.getDeviceId());
        hrrestDto.setEndTime(hrrestVo.getEndTime());
        hrrestDto.setFamilyMemberId(hrrestVo.getFamilyMemberId());
        hrrestDto.setHrrest(hrrestVo.getHrrest());
        hrrestDto.setStartTime(hrrestVo.getStartTime());
        hrrestDto.setUserId(hrrestVo.getUserId());
        EventBus.publish(HrrestDto.EVENT_NAME,hrrestDto);
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

        List<ReportDto> report = hrrestClient.report(familyMemberId, userId, reportUnits);
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
