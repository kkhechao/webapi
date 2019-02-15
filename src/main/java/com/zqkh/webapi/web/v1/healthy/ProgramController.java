package com.zqkh.webapi.web.v1.healthy;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.domain.dto.program.*;
import com.zqkh.webapi.context.domain.vo.program.*;
import com.zqkh.webapi.context.service.ProgramService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 解决方案控制层
 *
 * @author 东来
 * @create 2018/5/15 0015
 */
@RestController
@RequestMapping("/healthy/program")
public class ProgramController {


    @Resource
    private ProgramService programService;


    /**
     * 查看成员解决方案列表
     * @param familyMemberId:成员编号
     * @param key:搜索关键字
     * @param done:是否完成
     * @param dayDone:当天完成情况
     * @param programSrcTypeEnum :方案来源那类型
     * @param src:方案来源
     * @param pageIndex:第几页
     * @param pageSize:每页显示多少条
     * @return
     */
    @GetMapping("")
    @ApiImplicitParams({@ApiImplicitParam(value = "家庭成员编号", name = "familyMemberId", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "搜索关键字", name = "key", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "是否完成", name = "done", paramType = "query", dataType = "boolean"),
            @ApiImplicitParam(value = "当前完成情况", name = "dayDone", paramType = "query", dataType = "boolean"),
            @ApiImplicitParam(value = "方案来源类型", name = "programSrcType", paramType = "query", dataType = "ProgramSrcTypeEnum"),
            @ApiImplicitParam(value = "方案来源", name = "src", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "第几页", name = "pageIndex", paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(value = "每页显示多少条", name = "pageSize", paramType = "query", dataType = "int", defaultValue = "20")
    })
    @ApiOperation(value = "api_healthy_program_search")
    @Anonymous
    PageResult<ProgramListDto> search(@RequestParam(value = "familyMemberId", required = false) String familyMemberId,
                                      @RequestParam(value = "key", required = false) String key,
                                      @RequestParam(value = "done", required = false) Boolean done,
                                      @RequestParam(value = "dayDone", required = false) Boolean dayDone,
                                      @RequestParam(value = "programSrcType", required = false) ProgramSrcTypeEnum programSrcTypeEnum,
                                      @RequestParam(value = "src", required = false) String src,
                                      @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        return programService.search(familyMemberId, key, done, dayDone, programSrcTypeEnum, src, pageIndex, pageSize);
    }


    /**
     * 查询方案详情
     *
     * @param id
     * @return
     */
    @GetMapping("/info")
    @ApiOperation(value = "api_healthy_program_info")
    @ApiImplicitParam(value = "方案编号", name = "id", paramType = "query", dataType = "String")
    ProgramInfoDto info(@RequestParam(value = "id", required = false) String id) {
        return programService.info(id);
    }

    ;

    /**
     * 方案反馈
     *
     * @param programFeedback
     * @return
     */
    @PostMapping("/feedback")
    @ApiOperation(value = "api_healthy_program_feedback")
    ProgramFeedBackDto feedback(@RequestBody ProgramFeedbackVo programFeedback) {
        return programService.feedback(programFeedback);
    }


    /**
     * 结束方案
     *
     * @return
     */
    @PostMapping("/end")
    @ApiOperation(value = "api_healthy_program_end")
    ProgramResultInfoDto end(@RequestBody EndProgramVo endProgramVo) {
        return programService.end(endProgramVo);
    }

    ;

    /**
     * 查看方案结果详情
     *
     * @param id
     * @return
     */
    @GetMapping("/end/info")
    @ApiOperation(value = "api_healthy_program_result_info")
    @ApiImplicitParams({@ApiImplicitParam(value = "家庭成员编号", name = "familyMemberId", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "方案结果编号", name = "id", paramType = "query", dataType = "String")
    })
    ProgramResultInfoDto resultInfo(@RequestParam(value = "id", required = false) String id) {
        return programService.resultInfo(id);
    }

    /**
     * 开启方案
     *
     */
    @PostMapping("/open")
    @ApiOperation(value = "api_healthy_program_open")
    void open(@RequestBody OpenProgramVo openProgramVo) {
        programService.open(openProgramVo);
    }

    @PostMapping("/copy")
    @ApiOperation(value = "api_healthy_program_copy")
    CopyProgramDto copy(@RequestBody CopyProgramVo copyProgramVo) {
        return programService.copy(copyProgramVo);
    }

}
