package com.zqkh.webapi.web.v1.healthy;

import com.zqkh.webapi.context.domain.vo.program.EditGramTaskDoneVo;
import com.zqkh.webapi.context.service.ProgramTaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 方案任务控制层
 *
 * @author 东来
 * @create 2018/6/8 0008
 */
@RestController
@RequestMapping("/healthy/program/task")
public class ProgramTaskController {


    @Resource
    private ProgramTaskService programTaskService;

    /**
     * 修改方案任务完成情况
     *
     */
    @PostMapping("/edit/done")
    @ApiOperation(value = "api_healthy_program_task_done")
    void editDone(@RequestBody EditGramTaskDoneVo editGramTaskDoneVo) {
        programTaskService.editDone(editGramTaskDoneVo);
    }

    ;


}
