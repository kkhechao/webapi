package com.zqkh.webapi.web.v1.healthy;

import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.domain.vo.test.paper.SubmitAnAnswerVo;
import com.zqkh.webapi.context.dto.healthy.test.paper.TestPaperInfoDto;
import com.zqkh.webapi.context.dto.healthy.test.paper.TestPaperListDto;
import com.zqkh.webapi.context.dto.healthy.test.paper.TestResultDto;
import com.zqkh.webapi.context.service.TestPaperService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 试卷控制层
 *
 * @author 东来
 * @create 2018/5/9 0009
 */
@RestController
@RequestMapping("/healthy/testPaper")
public class TestPaperController {


    @Resource
    private TestPaperService testPaperService;

    /**
     * 试卷列表
     *
     * @param familyMemberId
     * @return
     */
    @GetMapping()
    @Anonymous
    @ApiOperation(value = "api_healthy_test_paper_list", notes = "试卷列表")
    @ApiImplicitParams({@ApiImplicitParam(value = "家庭成员编号", name = "familyMemberId", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "试卷类型编号", name = "typeId", paramType = "query", dataType = "String")})
    TestPaperListDto getTestPaperList(@RequestParam(name = "familyMemberId", required = false) String familyMemberId, @RequestParam(name = "typeId", required = false) String typeId) {
        return testPaperService.getTestPaperList(familyMemberId, typeId);
    }

    /**
     * 试卷详情
     *
     * @param id
     * @param familyMemberId
     * @return
     */
    @GetMapping("/info")
    @Anonymous
    @ApiOperation(value = "api_healthy_test_paper_info", notes = "试卷详情")
    @ApiImplicitParams({@ApiImplicitParam(value = "家庭成员编号", name = "familyMemberId", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "试卷编号", name = "id", paramType = "query", dataType = "String")})
    TestPaperInfoDto getTestPaperInfo(@RequestParam(name = "id", required = false) String id, @RequestParam(name = "familyMemberId", required = false) String familyMemberId) {
        return testPaperService.getTestPaperInfo(id, familyMemberId);
    }


    /**
     * 获取试卷答题结果
     *
     * @param id:答题结果编号
     * @return
     */
    @Anonymous
    @GetMapping("/testResult/info")
    @ApiOperation(value = "api_healthy_test_result_info", notes = "获取试卷答题详情")
    @ApiImplicitParam(name = "id", value = "答题结果编号", required = true, paramType = "query", dataType = "String")
    TestResultDto getTestResult(@RequestParam(name = "id", required = false) String id) {
        return testPaperService.getTestResult(id);
    }

    /**
     * 提交试卷答案
     *
     * @param submitAnAnswerVo
     */
    @PostMapping("/testResult/submit")
    @ApiOperation(value = "api_healthy_test_result_submit", notes = "提交试卷答案")
    @Anonymous
    TestResultDto submitAnanswer(@RequestBody SubmitAnAnswerVo submitAnAnswerVo) {
        return testPaperService.submitAnAnswer(submitAnAnswerVo);
    }

}
