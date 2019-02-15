package com.zqkh.webapi.context.service.impl;

import com.zqkh.archive.feign.DietHistoryClient;
import com.zqkh.archive.feign.dto.DietHistoryDto;
import com.zqkh.archive.feign.vo.DietHistoryVo;
import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.domain.dto.healthhistory.diet.ApiDietHistoryDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.diet.ApiDietHistoryPageVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.diet.ApiDietHistoryVo;
import com.zqkh.webapi.context.service.DietHistoryService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 健康史 Service
 */
@Service
public class DietHistoryServiceImpl implements DietHistoryService {

    @Autowired
    private DietHistoryClient client;

    private DozerBeanMapper modelMapper = new DozerBeanMapper();


    @Override
    public void addDietHistory(ApiDietHistoryVo inVo) {
        DietHistoryVo transVo = modelMapper.map(inVo, DietHistoryVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        client.addDietHistory(transVo);
    }

    @Override
    public PageResult<ApiDietHistoryDto> getDietHistories(ApiDietHistoryPageVo inVo) {
        DietHistoryVo transVo = modelMapper.map(inVo, DietHistoryVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        PageResult<DietHistoryDto> resDtos = client.getDietHistories(transVo);

        //region 将 Fegin 的响应结果，转换为 API 的响应 Dto
        List<ApiDietHistoryDto> outDtos = resDtos.getList()
                .stream()
                .map(resDto -> modelMapper.map(resDto, ApiDietHistoryDto.class))
                .collect(Collectors.toList());
        //endregion

        return new PageResult<>(resDtos.getTotalCount(), resDtos.getPageSize(), outDtos);
    }

    @Override
    public void deleteDietHistory(String id) {
        client.deleteDietHistory(id);
    }
}
