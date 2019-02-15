package com.zqkh.webapi.context.service.impl;

import com.zqkh.archive.feign.GeneticHistoryClient;
import com.zqkh.archive.feign.dto.GeneticHistoryDto;
import com.zqkh.archive.feign.vo.GeneticHistoryVo;
import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.domain.dto.healthhistory.genetic.ApiGeneticHistoryDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.genetic.ApiGeneticHistoryPageVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.genetic.ApiGeneticHistoryVo;
import com.zqkh.webapi.context.service.GeneticHistoryService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 家族病史
 */
@Service
public class GeneticHistoryServiceImpl implements GeneticHistoryService {

    @Autowired
    private GeneticHistoryClient client;

    private DozerBeanMapper modelMapper = new DozerBeanMapper();

    @Override
    public void addGeneticHistory(ApiGeneticHistoryVo inVo) {
        GeneticHistoryVo transVo = modelMapper.map(inVo, GeneticHistoryVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        client.addGeneticHistory(transVo);
    }

    @Override
    public void editGeneticHistory(ApiGeneticHistoryVo inVo) {
        GeneticHistoryVo transVo = modelMapper.map(inVo, GeneticHistoryVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        client.editGeneticHistory(transVo);
    }

    @Override
    public void deleteGeneticHistory(String id) {
        client.deleteGeneticHistory(id);
    }

    @Override
    public PageResult<ApiGeneticHistoryDto> getGeneticHistories(ApiGeneticHistoryPageVo inVo) {
        GeneticHistoryVo transVo = modelMapper.map(inVo, GeneticHistoryVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        PageResult<GeneticHistoryDto> pageResult = client.getGeneticHistories(transVo);

        List<ApiGeneticHistoryDto> outDtos = pageResult.getList()
                .stream()
                .map(resDto -> modelMapper.map(resDto, ApiGeneticHistoryDto.class))
                .collect(Collectors.toList());


        return new PageResult<>(pageResult.getTotalCount(), pageResult.getPageSize(), outDtos);
    }
}
