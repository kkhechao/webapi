package com.zqkh.webapi.context.service.impl;

import com.zqkh.archive.feign.SmokingInfoClient;
import com.zqkh.archive.feign.dto.SmokingInfoDto;
import com.zqkh.archive.feign.vo.SmokingInfoVo;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.domain.dto.healthhistory.smoking.ApiSmokingInfoDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.smoking.ApiSmokingInfoListVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.smoking.ApiSmokingInfoVo;
import com.zqkh.webapi.context.service.SmokingInfoService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmokingInfoServiceImpl implements SmokingInfoService {

    @Autowired
    private SmokingInfoClient client;

    private DozerBeanMapper modelMapper = new DozerBeanMapper();

    @Override
    public List<ApiSmokingInfoDto> getSmokingInfo(ApiSmokingInfoListVo inVo) {
        SmokingInfoVo transVo = modelMapper.map(inVo, SmokingInfoVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        List<SmokingInfoDto> resDtos = client.getSmokingInfo(transVo);

        List<ApiSmokingInfoDto> dtos = resDtos.stream()
                .map(resDto -> modelMapper.map(resDto, ApiSmokingInfoDto.class))
                .collect(Collectors.toList());

        return dtos;
    }

    @Override
    public void addSmokingInfo(ApiSmokingInfoVo inVo) {
        SmokingInfoVo transVo = modelMapper.map(inVo, SmokingInfoVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        client.addSmokingInfo(transVo);
    }

    @Override
    public void deleteSmokingInfo(String id) {
        client.deleteSmokingInfo(id);
    }
}
