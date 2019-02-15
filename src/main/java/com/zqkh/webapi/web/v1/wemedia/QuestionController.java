package com.zqkh.webapi.web.v1.wemedia;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.domain.vo.wemedia.question.QuestionVo;
import com.zqkh.webapi.context.service.QuestionService;
import com.zqkh.wemedia.feign.dto.question.QuestionInfoDto;
import com.zqkh.wemedia.feign.dto.question.QuestionListDto;
import com.zqkh.wemedia.feign.dto.question.UserQuestionListDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * C端问题
 *
 * @author 悭梵
 * @date Created in 2018-06-28 15:32
 */
@RestController
@RequestMapping("/me/media/question")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    /**
     * 提问
     *
     * @param questionVo
     */
    @PostMapping("/add")
    @ApiOperation(value = "api_question_add", notes = "提问")
    public void question(@RequestBody QuestionVo questionVo) {
        questionService.question(questionVo);
    }

    /**
     * 删除问题
     *
     * @param id
     */
    @PostMapping("/delete")
    @ApiOperation(value = "api_question_remove", notes = "删除问题")
    @ApiImplicitParam(name = "id", value = "问题标识", paramType = "body")
    public void delete(@RequestBody String id) {
        questionService.delete(id);
    }

    /**
     * C端-问题详情
     *
     * @param id
     * @return
     */
    @Anonymous
    @GetMapping("/info")
    @ApiOperation(value = "api_question_info", notes = "问题详情")
    @ApiImplicitParam(name = "id", value = "问题标识", paramType = "query")
    public QuestionInfoDto info(@RequestParam(name = "id", required = false) String id) {
        return questionService.info(id);
    }

    /**
     * C端-我的问题列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/user")
    @ApiOperation(value = "api_question_user_list", notes = "我的问题列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "第几页", required = false, defaultValue = "1", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = false, defaultValue = "20", paramType = "query", dataType = "String")
    })
    public PageResult<UserQuestionListDto> userList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                                     @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return questionService.userList(pageIndex, pageSize);
    }

    /**
     * C端-我的回答列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/user/answer")
    @ApiOperation(value = "api_question_user_answer_list", notes = "我的回答列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "第几页", required = false, defaultValue = "1", paramType = "query", dataType = "String")
            , @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = false, defaultValue = "20", paramType = "query", dataType = "String")
    })
    public PageResult<QuestionListDto> userAnswerList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                                       @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return questionService.userAnswerList(pageIndex, pageSize);
    }

    /**
     * C端-问题列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Anonymous
    @GetMapping("")
    @ApiOperation(value = "api_question_list", notes = "问题列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "第几页", required = false, defaultValue = "1", paramType = "query", dataType = "String")
            , @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = false, defaultValue = "20", paramType = "query", dataType = "String")
    })
    public PageResult<QuestionListDto> list(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                            @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return questionService.list(pageIndex, pageSize);
    }
}
