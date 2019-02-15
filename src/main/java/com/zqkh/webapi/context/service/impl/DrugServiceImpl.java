package com.zqkh.webapi.context.service.impl;

import com.zqkh.archive.feign.DrugClient;
import com.zqkh.archive.feign.dto.DrugDto;
import com.zqkh.archive.feign.vo.DrugVo;
import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.domain.dto.healthhistory.drug.ApiDrugDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.drug.ApiDrugVo;
import com.zqkh.webapi.context.service.DrugService;
import org.apache.commons.collections4.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrugServiceImpl implements DrugService {

    @Autowired
    private DrugClient client;

    private DozerBeanMapper modelMapper = new DozerBeanMapper();


    @Override
    public ApiDrugDto selectById(String id) {
        DrugDto resDto = client.selectById(id);

        return modelMapper.map(resDto, ApiDrugDto.class);
    }

    @Override
    public String selectNameById(String id) {
        return client.selectNameById(id);
    }

    @Override
    public List<ApiDrugDto> selectBySymptomId(String symptomId) {
        List<DrugDto> resDtos = client.selectBySymptomId(symptomId);

        if (CollectionUtils.isEmpty(resDtos)) {
            return null;
        }

        List<ApiDrugDto> outDtos = resDtos.stream()
                .map(resDto -> modelMapper.map(resDto, ApiDrugDto.class))
                .collect(Collectors.toList());

        return outDtos;
    }

    @Override
    public PageResult<ApiDrugDto> pageBySymptomId(ApiDrugVo inVo) {
        DrugVo transVo = modelMapper.map(inVo, DrugVo.class);

        PageResult<DrugDto> pageResult = client.pageBySymptomId(transVo);

        List<ApiDrugDto> outDtos = pageResult.getList()
                .stream()
                .map(resDto -> modelMapper.map(resDto, ApiDrugDto.class))
                .collect(Collectors.toList());

        return new PageResult<>(pageResult.getTotalCount(), pageResult.getPageSize(), outDtos);
    }
}
