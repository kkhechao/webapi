package com.zqkh.webapi.web.v1.archive;

import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.domain.dto.healthhistory.takehorn.ApiTakeHornDto;
import com.zqkh.webapi.context.domain.dto.healthhistory.takehorn.ApiTakeHornInfoDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.takehorn.ApiTakeHornHistoryInfoVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.takehorn.ApiTakeHornInfoListVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.takehorn.ApiTakeHornVo;
import com.zqkh.webapi.context.service.TakeHornInfoService;
import com.zqkh.webapi.context.service.TakeHornService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 饮酒史 Controller
 */
@RestController
@RequestMapping("/takehorn/history")
public class TakeHornController {

    @Resource
    private TakeHornService service;

    @Resource
    private TakeHornInfoService takeHornInfoService;

    /**
     * 获取饮酒史信息
     */
    @Anonymous
    @ApiOperation(value = "api_get_take_horn", notes = "获取饮酒史信息")
    @PostMapping("get")
    ApiTakeHornDto getTakeHorn(@RequestBody ApiTakeHornHistoryInfoVo inVo) {
        ApiTakeHornDto outDto = service.getTakeHorn(inVo);

        List<ApiTakeHornInfoDto> takeHornInfoList = takeHornInfoService.getTakeHornInfo(new ApiTakeHornInfoListVo(inVo.getFamilyMemberId()));

        takeHornInfoList.forEach(item -> {
            item.setUserId(null);
            item.setFamilyMemberId(null);
            item.setUpdateTime(null);
        });

        if (Objects.isNull(outDto)) {
            return null;
        }

        outDto.setTakeHornInfoList(takeHornInfoList);

        return outDto;
    }

    /**
     * 添加记录
     */
    @Anonymous
    @ApiOperation(value = "api_add_take_horn", notes = "添加记录")
    @PostMapping("add")
    void addTakeHorn(@RequestBody ApiTakeHornVo inVo) {
        service.addTakeHorn(inVo);
    }

    /**
     * 修改记录
     */
    @Anonymous
    @ApiOperation(value = "api_edit_take_horn", notes = "修改记录")
    @PostMapping("edit")
    void editTakeHorn(@RequestBody ApiTakeHornVo inVo) {
        service.editTakeHorn(inVo);
    }

    /**
     * 删除记录
     */
    @Anonymous
    @ApiOperation(value = "api_delete_take_horn", notes = "删除记录")
    @PostMapping("del")
    void deleteTakeHorn(@RequestBody String id) {
        service.deleteTakeHorn(id);
    }

}
