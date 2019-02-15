package com.zqkh.webapi.web.v1.archive;

import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.domain.dto.healthhistory.smoking.ApiSmokingHistoryDto;
import com.zqkh.webapi.context.domain.dto.healthhistory.smoking.ApiSmokingInfoDto;
import com.zqkh.webapi.context.domain.vo.healthhistory.smoking.ApiSmokingHistoryInfoVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.smoking.ApiSmokingInfoListVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.smoking.ApiSmokingInfoVo;
import com.zqkh.webapi.context.service.SmokingHistoryService;
import com.zqkh.webapi.context.service.SmokingInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 吸烟信息 Controller
 */
@RestController
@RequestMapping("/smoking/info")
public class SmokingInfoController {

    @Resource
    private SmokingInfoService service;

    @Resource
    private SmokingHistoryController historyController;

    /**
     * 获取吸烟信息列表
     */
    @Anonymous
    @ApiOperation(value = "api_get_smoking_info", notes = "获取吸烟信息列表")
    @PostMapping("index")
    List<ApiSmokingInfoDto> getSmokingInfo(@RequestBody ApiSmokingInfoListVo inVo) {
        return service.getSmokingInfo(inVo);
    }

    /**
     * 添加记录
     */
    @Anonymous
    @ApiOperation(value = "api_add_smoking_info", notes = "添加记录")
    @PostMapping("add")
    ApiSmokingHistoryDto addSmokingInfo(@RequestBody ApiSmokingInfoVo inVo) {
        service.addSmokingInfo(inVo);

        return historyController.getSmokingHistories(new ApiSmokingHistoryInfoVo(inVo.getFamilyMemberId()));
    }

    /**
     * 删除记录
     */
    @Anonymous
    @ApiOperation(value = "api_delete_smoking_info", notes = "删除记录")
    @PostMapping("del")
    void deleteSmokingInfo(@RequestBody String id) {
        service.deleteSmokingInfo(id);
    }

}
