package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.domain.dto.healthhistory.drug.ApiDrugDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.drug.ApiDrugVo;

import java.util.List;

public interface DrugService {

    ApiDrugDto selectById(String id);

    String selectNameById(String id);

    List<ApiDrugDto> selectBySymptomId(String symptomId);

    PageResult<ApiDrugDto> pageBySymptomId(ApiDrugVo inVo);

}
