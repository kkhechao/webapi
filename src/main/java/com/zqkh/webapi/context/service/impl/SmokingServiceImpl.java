package com.zqkh.webapi.context.service.impl;

import com.zqkh.archive.feign.SmokingHistoryClient;
import com.zqkh.archive.feign.dto.SmokingHistoryDto;
import com.zqkh.archive.feign.vo.SmokingHistoryVo;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.domain.dto.healthhistory.smoking.ApiSmokingHistoryDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.smoking.ApiSmokingHistoryInfoVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.smoking.ApiSmokingHistoryVo;
import com.zqkh.webapi.context.service.SmokingHistoryService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 吸烟史 Service
 */
@Service
public class SmokingServiceImpl implements SmokingHistoryService {

    @Autowired
    private SmokingHistoryClient client;

    private DozerBeanMapper modelMapper = new DozerBeanMapper();


    @Override
    public void addSmokingHistory(ApiSmokingHistoryVo inVo) {
        SmokingHistoryVo transVo = modelMapper.map(inVo, SmokingHistoryVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        client.addSmokingHistory(transVo);
    }

    @Override
    public void editSmokingHistory(ApiSmokingHistoryVo inVo) {
        SmokingHistoryVo transVo = modelMapper.map(inVo, SmokingHistoryVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        client.editSmokingHistory(transVo);
    }

    @Override
    public void deleteSmokingHistory(String id) {
        client.deleteSmokingHistory(id);
    }

    @Override
    public ApiSmokingHistoryDto getSmokingHistory(ApiSmokingHistoryInfoVo inVo) {
        SmokingHistoryVo transVo = modelMapper.map(inVo, SmokingHistoryVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        SmokingHistoryDto dto = client.getSmokingHistory(transVo);

        if (Objects.isNull(dto)) {
            return null;
        }

        return modelMapper.map(dto, ApiSmokingHistoryDto.class);
    }
}
