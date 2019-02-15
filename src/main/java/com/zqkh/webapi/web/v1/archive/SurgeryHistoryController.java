package com.zqkh.webapi.web.v1.archive;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.domain.dto.healthhistory.surgery.ApiSurgeryHistoryDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.surgery.ApiSurgeryHistoryPageVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.surgery.ApiSurgeryHistoryVo;
import com.zqkh.webapi.context.service.SurgeryHistoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 手术史 Controller
 */
@RestController
@RequestMapping("/surgery/history")
public class SurgeryHistoryController {


    @Resource
    private SurgeryHistoryService service;


    /**
     * 获取分页信息
     */
    @Anonymous
    @ApiOperation(value = "api_get_surgery_histories", notes = "获取分页信息")
    @PostMapping("index")
    PageResult<ApiSurgeryHistoryDto> getSurgeryHistories(@RequestBody ApiSurgeryHistoryPageVo inVo) {
        return service.getSurgeryHistories(inVo);
    }

    /**
     * 添加记录
     */
    @Anonymous
    @ApiOperation(value = "api_add_surgery_history", notes = "添加记录")
    @PostMapping("add")
    void addSurgeryHistory(@RequestBody ApiSurgeryHistoryVo inVo) {
        service.addSurgeryHistory(inVo);
    }

    /**
     * 修改记录
     */
    @Anonymous
    @ApiOperation(value = "api_edit_surgery_history", notes = "修改记录")
    @PostMapping("edit")
    void editSurgeryHistory(@RequestBody ApiSurgeryHistoryVo inVo) {
        service.editSurgeryHistory(inVo);
    }

    /**
     * 删除记录
     */
    @Anonymous
    @ApiOperation(value = "api_delete_surgery_history", notes = "删除记录")
    @PostMapping("del")
    void deleteSurgeryHistory(@RequestBody String id) {
        service.deleteSurgeryHistory(id);
    }

}
