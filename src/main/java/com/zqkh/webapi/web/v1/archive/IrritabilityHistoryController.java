package com.zqkh.webapi.web.v1.archive;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.domain.dto.healthhistory.irritability.ApiIrritabilityHistoryDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.irritability.ApiIrritabilityHistoryPageVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.irritability.ApiIrritabilityHistoryVo;
import com.zqkh.webapi.context.service.IrritabilityHistoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 过敏史  Controller
 */
@RestController
@RequestMapping("/irritability/history")
public class IrritabilityHistoryController {

    @Resource
    private IrritabilityHistoryService service;

    /**
     * 获取分页信息
     */
    @Anonymous
    @ApiOperation(value = "api_get_irritability_histories", notes = "获取分页信息")
    @PostMapping("index")
    PageResult<ApiIrritabilityHistoryDto> getIrritabilityHistories(@RequestBody ApiIrritabilityHistoryPageVo inVo) {
        return service.getIrritabilityHistories(inVo);
    }

    /**
     * 添加记录
     */
    @Anonymous
    @ApiOperation(value = "api_add_irritability_history", notes = "添加记录")
    @PostMapping("add")
    void addIrritabilityHistory(@RequestBody ApiIrritabilityHistoryVo inVo) {
        service.addIrritabilityHistory(inVo);
    }

    /**
     * 修改记录
     */
    @Anonymous
    @ApiOperation(value = "api_edit_irritability_history", notes = "修改记录")
    @PostMapping("edit")
    void editIrritabilityHistory(@RequestBody ApiIrritabilityHistoryVo inVo) {
        service.editIrritabilityHistory(inVo);
    }

    /**
     * 删除记录
     */
    @Anonymous
    @ApiOperation(value = "api_delete_irritability_history", notes = "删除记录")
    @PostMapping("del")
    void deleteIrritabilityHistory(@RequestBody String id) {
        service.deleteIrritabilityHistory(id);
    }

}
