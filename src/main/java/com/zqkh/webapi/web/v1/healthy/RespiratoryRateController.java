package com.zqkh.webapi.web.v1.healthy;

import com.zqkh.healthy.feign.dto.bracelet.app.ReportUnits;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.domain.vo.bracelet.HrrestVo;
import com.zqkh.webapi.context.domain.vo.bracelet.RespiratoryRateVo;
import com.zqkh.webapi.context.dto.healthy.bracelet.ReportResultDto;
import com.zqkh.webapi.context.service.RespiratoryRateService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 呼吸频率
 *
 * @author: zhangchao
 * @time: 2018-07-18 18:56
 **/
@Anonymous
@RestController
@RequestMapping("/healthy/respiratoryRate")
public class RespiratoryRateController {


    @Autowired
    private RespiratoryRateService respiratoryRateService;

    @PostMapping("")
    @ApiOperation(value = "api_healthy_respiratory_rate_save")
    public void save(@RequestBody RespiratoryRateVo vo){
        respiratoryRateService.save(vo);
    }


    @GetMapping("/report")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "家庭成员", name = "familyMemberId", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "用户", name = "userId", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "单元 天/DAY, 周/WEEK ,月/MONTH", name = "reportUnits", paramType = "query", dataType = "ReportUnits"),
    })
    @ApiOperation(value = "api_healthy_hrrest_report")
    public List<ReportResultDto> report(@RequestParam(value = "familyMemberId",required = false) String familyMemberId,
                                        @RequestParam(value = "userId",required = false)String userId,
                                        @RequestParam(value = "reportUnits",required = false)ReportUnits reportUnits ){
        return respiratoryRateService.report(userId,familyMemberId,reportUnits);
    }

}
