package com.zqkh.webapi.web.v1.archive;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.domain.dto.healthhistory.disease.ApiDiseaseMedicationHistoryDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.disease.ApiDiseaseMedicationHistoryPageVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.disease.ApiDiseaseMedicationHistoryVo;
import com.zqkh.webapi.context.service.DiseaseMedicationHistoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 疾病用药史
 */
@RestController
@RequestMapping("/disease/history")
public class DiseaseMedicationHistoryController {

    @Resource
    private DiseaseMedicationHistoryService service;


    /**
     * 添加记录
     */
    @Anonymous
    @ApiOperation(value = "api_add_disease_medication_history", notes = "添加记录")
    @PostMapping("add")
    void addDiseaseMedicationHistory(@RequestBody ApiDiseaseMedicationHistoryVo inVo) {
        service.addDiseaseMedicationHistory(inVo);
    }

    /**
     * 获取分页信息
     */
    @Anonymous
    @ApiOperation(value = "api_get_disease_medication_histories", notes = "获取分页信息")
    @PostMapping("index")
    PageResult<ApiDiseaseMedicationHistoryDto> getDiseaseMedicationHistories(@RequestBody ApiDiseaseMedicationHistoryPageVo inVo) {
        return service.getDiseaseMedicationHistories(inVo);
    }

    /**
     * 修改记录信息
     */
    @Anonymous
    @ApiOperation(value = "api_edit_disease_medication_history", notes = "修改记录信息")
    @PostMapping("edit")
    void editDiseaseMedicationHistory(@RequestBody ApiDiseaseMedicationHistoryVo inVo) {
        service.editDiseaseMedicationHistory(inVo);
    }

    /**
     * 修改用药史信息
     */
    @Anonymous
    @ApiOperation(value = "api_edit_medication_histories", notes = "修改用药史信息")
    @PostMapping("medication/edit")
    void editMedicationHistories(@RequestBody ApiDiseaseMedicationHistoryVo inVo) {
        service.editMedicationHistories(inVo);
    }

    /**
     * 删除记录信息
     */
    @Anonymous
    @ApiOperation(value = "api_delete_disease_medication_history", notes = "删除记录信息")
    @PostMapping("del")
    void deleteDiseaseMedicationHistory(@RequestBody String id) {
        service.deleteDiseaseMedicationHistory(id);
    }

}
