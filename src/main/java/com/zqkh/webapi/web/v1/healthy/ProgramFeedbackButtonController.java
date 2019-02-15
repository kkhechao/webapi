package com.zqkh.webapi.web.v1.healthy;

import com.zqkh.webapi.context.domain.dto.program.ProgramFeedbackButtonListDto;
import com.zqkh.webapi.context.service.ProgramFeedbackButtonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 方案反馈按钮控制层
 *
 * @author 东来
 * @create 2018/7/4 0004
 */
@RestController
@RequestMapping("/healthy/program/feedback/button")
public class ProgramFeedbackButtonController {

    @Resource
    private ProgramFeedbackButtonService programFeedbackButtonService;

    /**
     * 获取所有方案反馈按钮列表
     *
     * @return
     */
    @GetMapping()
    @ApiOperation(value = "api_healthy_program_feedback_button")
    List<ProgramFeedbackButtonListDto> all() {
        return programFeedbackButtonService.all();
    }

}
