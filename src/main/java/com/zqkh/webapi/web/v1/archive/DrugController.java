package com.zqkh.webapi.web.v1.archive;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.domain.dto.healthhistory.drug.ApiDrugDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.drug.ApiDrugVo;
import com.zqkh.webapi.context.service.DrugService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 药品 Controller
 */
@RestController
@RequestMapping("/drug")
public class DrugController {

    @Autowired
    private DrugService service;

    @Anonymous
    @ApiOperation(value = "api_drug_get", notes = "根据药品id获取药品信息")
    @PostMapping("get")
    public ApiDrugDto selectById(@RequestBody String id) {
        return service.selectById(id);
    }

    @Anonymous
    @ApiOperation(value = "api_drug_get_name", notes = "根据药品id获取药品名称")
    @PostMapping("getName")
    public String selectNameById(@RequestBody String id) {
        return service.selectNameById(id);
    }

    @Anonymous
    @ApiOperation(value = "api_drug_symptoms", notes = "根据疾病Id获取药品信息列表")
    @PostMapping("drugs")
    public List<ApiDrugDto> selectBySymptomId(@RequestBody String symptomId) {
        return service.selectBySymptomId(symptomId);
    }

    @Anonymous
    @ApiOperation(value = "api_drug_index", notes = "通过疾病id和已选药品 分页获取药品列表")
    @PostMapping("index")
    public PageResult<ApiDrugDto> pageBySymptomId(@RequestBody ApiDrugVo inVo) {
        return service.pageBySymptomId(inVo);
    }

}
