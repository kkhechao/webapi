package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.domain.dto.ApiBaseConfigDto;
import com.zqkh.webapi.context.domain.dto.healthhistory.symptom.ApiSymptomDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.symptom.ApiSymptomPageVo;

import java.util.List;

/**
 * 疾病 Service
 */
public interface SymptomService {

    String selectNameById(String id);

    ApiSymptomDto selectById(String id);

    PageResult<ApiSymptomDto> selectByPlace(
            ApiSymptomPageVo inVO
    );

    List<ApiBaseConfigDto> selectAllPlaceInfo();

}
