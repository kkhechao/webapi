package com.zqkh.webapi.context.service.impl;

import com.zqkh.archive.feign.SymptomClient;
import com.zqkh.archive.feign.dto.BaseConfigDto;
import com.zqkh.archive.feign.dto.SymptomDto;
import com.zqkh.archive.feign.vo.SymptomVo;
import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.domain.dto.ApiBaseConfigDto;
import com.zqkh.webapi.context.domain.dto.healthhistory.symptom.ApiSymptomDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.symptom.ApiSymptomPageVo;
import com.zqkh.webapi.context.service.SymptomService;
import org.apache.commons.collections4.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SymptomServiceImpl implements SymptomService {

    @Autowired
    private SymptomClient client;

    private DozerBeanMapper modelMapper = new DozerBeanMapper();

    @Override
    public String selectNameById(String id) {
        return client.selectNameById(id);
    }

    @Override
    public ApiSymptomDto selectById(String id) {
        SymptomDto resDto = client.selectById(id);

        return modelMapper.map(resDto, ApiSymptomDto.class);
    }

    @Override
    public PageResult<ApiSymptomDto> selectByPlace(ApiSymptomPageVo inVo) {
        SymptomVo transVo = modelMapper.map(inVo, SymptomVo.class);

        PageResult<SymptomDto> pageResult = client.selectByPlace(transVo);

        List<ApiSymptomDto> outDtos = pageResult.getList().stream()
                .map(resDto -> modelMapper.map(resDto, ApiSymptomDto.class))
                .collect(Collectors.toList());

        return new PageResult<>(pageResult.getTotalCount(), pageResult.getPageSize(), outDtos);
    }

    @Override
    public List<ApiBaseConfigDto> selectAllPlaceInfo() {
        List<BaseConfigDto> resDtos = client.selectAllPlaceInfo();

        if (CollectionUtils.isEmpty(resDtos)) {
            return null;
        }

        List<ApiBaseConfigDto> outDtos = resDtos.stream()
                .map(resDto -> modelMapper.map(resDto, ApiBaseConfigDto.class))
                .collect(Collectors.toList());

        return outDtos;
    }
}
