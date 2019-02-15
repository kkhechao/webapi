package com.zqkh.webapi.web.v1.archive;

import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.domain.dto.healthhistory.smoking.ApiSmokingHistoryDto;
import com.zqkh.webapi.context.domain.dto.healthhistory.smoking.ApiSmokingInfoDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.smoking.ApiSmokingHistoryInfoVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.smoking.ApiSmokingHistoryVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.smoking.ApiSmokingInfoListVo;
import com.zqkh.webapi.context.service.SmokingHistoryService;
import com.zqkh.webapi.context.service.SmokingInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 吸烟史 Controller
 */
@RestController
@RequestMapping("/smoking/history")
public class SmokingHistoryController {

    @Resource
    private SmokingHistoryService service;

    @Resource
    private SmokingInfoService smokingInfoService;

    /**
     * 获取分页信息
     */
    @Anonymous
    @ApiOperation(value = "api_get_smoking_history", notes = "获取吸烟史信息")
    @PostMapping("get")
    ApiSmokingHistoryDto getSmokingHistories(@RequestBody ApiSmokingHistoryInfoVo inVo) {
        ApiSmokingHistoryDto outDto = service.getSmokingHistory(inVo);

        List<ApiSmokingInfoDto> smokingInfoList = smokingInfoService.getSmokingInfo(new ApiSmokingInfoListVo(inVo.getFamilyMemberId()));

        smokingInfoList.forEach(item -> {
            item.setUpdateTime(null);
            item.setFamilyMemberId(null);
            item.setUserId(null);
        });

        if (Objects.isNull(outDto)){
            return null;
        }

        outDto.setSmokingInfoList(smokingInfoList);

        return outDto;
    }

    /**
     * 添加记录
     */
    @Anonymous
    @ApiOperation(value = "api_add_smoking_history", notes = "添加记录")
    @PostMapping("add")
    void addSmokingHistory(@RequestBody ApiSmokingHistoryVo inVo) {
        service.addSmokingHistory(inVo);
    }

    /**
     * 修改记录
     */
    @Anonymous
    @ApiOperation(value = "api_edit_smoking_history", notes = "修改记录")
    @PostMapping("edit")
    void editSmokingHistory(@RequestBody ApiSmokingHistoryVo inVo) {
        service.editSmokingHistory(inVo);
    }

    /**
     * 删除记录
     */
    @Anonymous
    @ApiOperation(value = "api_delete_smoking_history", notes = "删除记录")
    @PostMapping("del")
    void deleteSmokingHistory(@RequestBody String id) {
        service.deleteSmokingHistory(id);
    }

}
