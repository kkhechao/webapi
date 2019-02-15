package com.zqkh.webapi.web.v1.archive;


import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.domain.dto.healthhistory.diet.ApiDietHistoryDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.diet.ApiDietHistoryPageVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.diet.ApiDietHistoryVo;
import com.zqkh.webapi.context.service.DietHistoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 饮食史
 */
@RestController
@RequestMapping("/diet/history")
public class DietHistoryController {

    @Resource
    private DietHistoryService service;


    /**
     * 添加记录
     */
    @Anonymous
    @ApiOperation(value = "api_add_diet_history", notes = "添加记录")
    @PostMapping("add")
    void addDietHistory(@RequestBody ApiDietHistoryVo inVo) {
        service.addDietHistory(inVo);
    }

    /**
     * 获取分页信息
     */
    @Anonymous
    @ApiOperation(value = "api_get_diet_histories", notes = "获取分页信息")
    @PostMapping("index")
    PageResult<ApiDietHistoryDto> getDietHistories(@RequestBody ApiDietHistoryPageVo inVo) {
        return service.getDietHistories(inVo);
    }

    /**
     * 删除记录信息
     */
    @Anonymous
    @ApiOperation(value = "api_delete_diet_history", notes = "删除记录信息")
    @PostMapping("del")
    void deleteDietHistory(@RequestBody String id) {
        service.deleteDietHistory(id);
    }
}
