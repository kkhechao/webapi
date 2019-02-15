package com.zqkh.webapi.web.v1.archive;

import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.domain.dto.healthhistory.takehorn.ApiTakeHornDto;
import com.zqkh.webapi.context.domain.dto.healthhistory.takehorn.ApiTakeHornInfoDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.takehorn.ApiTakeHornHistoryInfoVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.takehorn.ApiTakeHornInfoListVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.takehorn.ApiTakeHornInfoVo;
import com.zqkh.webapi.context.service.TakeHornInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 饮酒信息 Controller
 */
@RestController
@RequestMapping("/takehorn/info")
public class TakeHornInfoController {

    @Resource
    private TakeHornInfoService service;

    @Resource
    private TakeHornController historyService;

    /**
     * 获取饮酒信息列表
     */
    @Anonymous
    @ApiOperation(value = "api_get_take_horn_info", notes = "获取饮酒信息列表")
    @PostMapping("index")
    List<ApiTakeHornInfoDto> getTakeHornInfo(@RequestBody ApiTakeHornInfoListVo inVo) {
        return service.getTakeHornInfo(inVo);
    }

    /**
     * 添加记录
     */
    @Anonymous
    @ApiOperation(value = "api_add_take_horn_info", notes = "添加记录")
    @PostMapping("add")
    ApiTakeHornDto addTakeHornInfo(@RequestBody ApiTakeHornInfoVo inVo) {
        service.addTakeHornInfo(inVo);

        return historyService.getTakeHorn(new ApiTakeHornHistoryInfoVo(inVo.getFamilyMemberId()));
    }

    /**
     * 删除记录
     */
    @Anonymous
    @ApiOperation(value = "api_delete_take_horn_info", notes = "删除记录")
    @PostMapping("del")
    void deleteTakeHornInfo(@RequestBody String id) {
        service.deleteTakeHornInfo(id);
    }

}
