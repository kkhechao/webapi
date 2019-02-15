package com.zqkh.webapi.context.service.impl;

import com.zqkh.archive.feign.TakeHornInfoClient;
import com.zqkh.archive.feign.dto.TakeHornInfoDto;
import com.zqkh.archive.feign.vo.TakeHornInfoVo;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.domain.dto.healthhistory.takehorn.ApiTakeHornInfoDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.takehorn.ApiTakeHornInfoListVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.takehorn.ApiTakeHornInfoVo;
import com.zqkh.webapi.context.service.TakeHornInfoService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TakeHornInfoServiceImpl implements TakeHornInfoService {

    @Autowired
    private TakeHornInfoClient client;

    private DozerBeanMapper modelMapper = new DozerBeanMapper();

    @Override
    public List<ApiTakeHornInfoDto> getTakeHornInfo(ApiTakeHornInfoListVo inVo) {
        TakeHornInfoVo transVo = modelMapper.map(inVo, TakeHornInfoVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        List<TakeHornInfoDto> resDtos = client.getTakeHornInfo(transVo);

        List<ApiTakeHornInfoDto> dtos = resDtos.stream()
                .map(resDto -> modelMapper.map(resDto, ApiTakeHornInfoDto.class))
                .collect(Collectors.toList());

        return dtos;
    }

    @Override
    public void addTakeHornInfo(ApiTakeHornInfoVo inVo) {
        TakeHornInfoVo transVo = modelMapper.map(inVo, TakeHornInfoVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        client.addTakeHornInfo(transVo);
    }

    @Override
    public void deleteTakeHornInfo(String id) {
        client.deleteTakeHornInfo(id);
    }
}
