package com.zqkh.webapi.web.v1.archive;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.domain.dto.ApiBaseConfigDto;
import com.zqkh.webapi.context.domain.dto.healthhistory.symptom.ApiSymptomDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.symptom.ApiSymptomPageVo;
import com.zqkh.webapi.context.service.SymptomService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 疾病 Controller
 */
@RestController
@RequestMapping("/symptom")
public class SymptomController {

    @Autowired
    private SymptomService service;

    @Anonymous
    @ApiOperation(value = "api_symptom_get_name", notes = "根据疾病id获取疾病名称")
    @PostMapping("getName")
    public String selectNameById(@RequestBody String id) {
        return service.selectNameById(id);
    }

    @Anonymous
    @ApiOperation(value = "api_symptom_get", notes = "根据疾病id获取疾病信息")
    @PostMapping("get")
    public ApiSymptomDto selectById(@RequestBody String id) {
        return service.selectById(id);
    }

    @Anonymous
    @ApiOperation(value = "api_symptom_symptoms", notes = "根据部位名称获取疾病信息列表")
    @PostMapping("symptoms")
    public PageResult<ApiSymptomDto> selectByPlace(
            @RequestBody ApiSymptomPageVo inVo
    ) {
        return service.selectByPlace(inVo);
    }

    @Anonymous
    @ApiOperation(value = "api_symptom_places_info", notes = "获取所有的部位配置信息")
    @PostMapping("placesInfo")
    public List<ApiBaseConfigDto> selectAllPlaceInfo() {
        return service.selectAllPlaceInfo();
    }

}
