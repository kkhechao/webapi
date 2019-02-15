package com.zqkh.webapi.web.v1.archive;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.domain.dto.healthhistory.genetic.ApiGeneticHistoryDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.genetic.ApiGeneticHistoryPageVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.genetic.ApiGeneticHistoryVo;
import com.zqkh.webapi.context.service.GeneticHistoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 家族病史  Controller
 */
@RestController
@RequestMapping("/genetic/history")
public class GeneticHistoryController {

    @Resource
    private GeneticHistoryService service;

    /**
     * 获取分页信息
     */
    @Anonymous
    @ApiOperation(value = "api_get_genetic_histories", notes = "获取分页信息")
    @PostMapping("index")
    PageResult<ApiGeneticHistoryDto> getSurgeryHistories(@RequestBody ApiGeneticHistoryPageVo inVo) {
        return service.getGeneticHistories(inVo);
    }

    /**
     * 添加记录
     */
    @Anonymous
    @ApiOperation(value = "api_add_genetic_history", notes = "添加记录")
    @PostMapping("add")
    void addSurgeryHistory(@RequestBody ApiGeneticHistoryVo inVo) {
        service.addGeneticHistory(inVo);
    }

    /**
     * 修改记录
     */
    @Anonymous
    @ApiOperation(value = "api_edit_genetic_history", notes = "修改记录")
    @PostMapping("edit")
    void editSurgeryHistory(@RequestBody ApiGeneticHistoryVo inVo) {
        service.editGeneticHistory(inVo);
    }

    /**
     * 删除记录
     */
    @Anonymous
    @ApiOperation(value = "api_delete_genetic_history", notes = "删除记录")
    @PostMapping("del")
    void deleteSurgeryHistory(@RequestBody String id) {
        service.deleteGeneticHistory(id);
    }

}