package com.zqkh.webapi.context.service.impl;

import com.zqkh.archive.feign.TakeHornClient;
import com.zqkh.archive.feign.dto.TakeHornDto;
import com.zqkh.archive.feign.vo.TakeHornVo;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.domain.dto.healthhistory.takehorn.ApiTakeHornDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.takehorn.ApiTakeHornHistoryInfoVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.takehorn.ApiTakeHornVo;
import com.zqkh.webapi.context.service.TakeHornService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 饮酒史 Service
 */
@Service
public class TakeHornServiceImpl implements TakeHornService {

    @Autowired
    private TakeHornClient client;

    private DozerBeanMapper modelMapper = new DozerBeanMapper();


    @Override
    public void addTakeHorn(ApiTakeHornVo inVo) {
        TakeHornVo transVo = modelMapper.map(inVo, TakeHornVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        client.addTakeHorn(transVo);
    }

    @Override
    public void editTakeHorn(ApiTakeHornVo inVo) {
        TakeHornVo transVo = modelMapper.map(inVo, TakeHornVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        client.editTakeHorn(transVo);
    }

    @Override
    public void deleteTakeHorn(String id) {
        client.deleteTakeHorn(id);
    }

    @Override
    public ApiTakeHornDto getTakeHorn(ApiTakeHornHistoryInfoVo inVo) {
        TakeHornVo transVo = modelMapper.map(inVo, TakeHornVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        TakeHornDto dto = client.getTakeHorn(transVo);

        if (Objects.isNull(dto)) {
            return null;
        }

        return modelMapper.map(dto, ApiTakeHornDto.class);
    }
}
