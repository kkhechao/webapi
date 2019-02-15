package com.zqkh.webapi.context.service.impl;

import com.zqkh.archive.feign.SurgeryHistoryClient;
import com.zqkh.archive.feign.dto.SurgeryHistoryDto;
import com.zqkh.archive.feign.vo.SurgeryHistoryVo;
import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.domain.dto.healthhistory.surgery.ApiSurgeryHistoryDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.surgery.ApiSurgeryHistoryPageVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.surgery.ApiSurgeryHistoryVo;
import com.zqkh.webapi.context.service.SurgeryHistoryService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 手术史 Service
 */
@Service
public class SurgeryHistoryServiceImpl implements SurgeryHistoryService {

    @Autowired
    private SurgeryHistoryClient client;

    private DozerBeanMapper modelMapper = new DozerBeanMapper();

    @Override
    public void addSurgeryHistory(ApiSurgeryHistoryVo inVo) {
        SurgeryHistoryVo transVo = modelMapper.map(inVo, SurgeryHistoryVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        client.addSurgeryHistory(transVo);
    }

    @Override
    public void editSurgeryHistory(ApiSurgeryHistoryVo inVo) {
        SurgeryHistoryVo transVo = modelMapper.map(inVo, SurgeryHistoryVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        client.editSurgeryHistory(transVo);
    }

    @Override
    public void deleteSurgeryHistory(String id) {
        client.deleteSurgeryHistory(id);
    }

    @Override
    public PageResult<ApiSurgeryHistoryDto> getSurgeryHistories(ApiSurgeryHistoryPageVo inVo) {
        SurgeryHistoryVo transVo = modelMapper.map(inVo, SurgeryHistoryVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        PageResult<SurgeryHistoryDto> pageResult = client.getSurgeryHistories(transVo);

        List<ApiSurgeryHistoryDto> outDtos = pageResult.getList()
                .stream()
                .map(resDto -> modelMapper.map(resDto, ApiSurgeryHistoryDto.class))
                .collect(Collectors.toList());

        return new PageResult<>(pageResult.getTotalCount(), pageResult.getPageSize(), outDtos);
    }
}
