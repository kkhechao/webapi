package com.zqkh.webapi.context.service.impl;

import com.zqkh.archive.feign.IrritabilityHistoryClient;
import com.zqkh.archive.feign.dto.IrritabilityHistoryDto;
import com.zqkh.archive.feign.vo.IrritabilityHistoryVo;
import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.domain.dto.healthhistory.irritability.ApiIrritabilityHistoryDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.irritability.ApiIrritabilityHistoryPageVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.irritability.ApiIrritabilityHistoryVo;
import com.zqkh.webapi.context.service.IrritabilityHistoryService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 过敏史 Service
 */
@Service
public class IrritabilityHistoryServiceImpl implements IrritabilityHistoryService {

    @Autowired
    private IrritabilityHistoryClient client;

    private DozerBeanMapper modelMapper = new DozerBeanMapper();

    @Override
    public PageResult<ApiIrritabilityHistoryDto> getIrritabilityHistories(ApiIrritabilityHistoryPageVo inVo) {
        IrritabilityHistoryVo transVo = modelMapper.map(inVo, IrritabilityHistoryVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        PageResult<IrritabilityHistoryDto> pageResult = client.getIrritabilityHistories(transVo);

        List<ApiIrritabilityHistoryDto> dtos = pageResult.getList()
                .stream()
                .map(resDto -> modelMapper.map(resDto, ApiIrritabilityHistoryDto.class))
                .collect(Collectors.toList());

        return new PageResult<>(pageResult.getTotalCount(), pageResult.getPageSize(), dtos);
    }

    @Override
    public void addIrritabilityHistory(ApiIrritabilityHistoryVo inVo) {
        IrritabilityHistoryVo transVo = modelMapper.map(inVo, IrritabilityHistoryVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        client.addIrritabilityHistory(transVo);
    }

    @Override
    public void editIrritabilityHistory(ApiIrritabilityHistoryVo inVo) {
        IrritabilityHistoryVo transVo = modelMapper.map(inVo, IrritabilityHistoryVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        client.editIrritabilityHistory(transVo);
    }

    @Override
    public void deleteIrritabilityHistory(String id) {
        client.deleteIrritabilityHistory(id);
    }
}
