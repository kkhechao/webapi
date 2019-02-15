package com.zqkh.webapi.web.v1.wemedia;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.domain.vo.wemedia.answer.AnswerVo;
import com.zqkh.webapi.context.service.AnswerService;
import com.zqkh.wemedia.feign.dto.answer.AnswerAnd3CommentListDto;
import com.zqkh.wemedia.feign.dto.answer.AnswerInfoDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * C端回答
 *
 * @author 悭梵
 * @date Created in 2018-06-28 15:58
 */
@RestController
@RequestMapping("/me/media/answer")
public class AnswerController {

    @Resource
    private AnswerService answerService;

    /**
     * 回答问题
     *
     * @param answerVo
     */
    @PostMapping("")
    @ApiOperation(value = "api_answer_add", notes = "回答")
    public void answer(@RequestBody AnswerVo answerVo) {
        answerService.answer(answerVo);
    }

    /**
     * 删除回答
     *
     * @param id
     */
    @PostMapping("/delete")
    @ApiOperation(value = "api_answer_remove", notes = "删除回答")
    @ApiImplicitParam(name = "id", value = "回答标识", paramType = "body")
    public void delete(@RequestBody String id) {
        answerService.delete(id);
    }

    /**
     * 点赞
     *
     * @param id
     */
    @PostMapping("/applaud")
    @ApiOperation(value = "api_answer_applaud", notes = "点赞")
    @ApiImplicitParam(name = "id", value = "回答标识", paramType = "body")
    public void applaud(@RequestBody String id) {
        answerService.applaud(id);
    }

    /**
     * 贬低
     *
     * @param id
     */
    @PostMapping("/belittle")
    @ApiOperation(value = "api_answer_belittle", notes = "踩")
    @ApiImplicitParam(name = "id", value = "回答标识", paramType = "body")
    public void belittle(@RequestBody String id) {
        answerService.belittle(id);
    }

    /**
     * C端-问题的回答列表，每条回答最多包含3条评论
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Anonymous
    @GetMapping("/question")
    @ApiOperation(value = "api_answer_list_by_question", notes = "指定问题的回答列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "questionId", value = "问题标识", paramType = "query"),
            @ApiImplicitParam(name = "pageIndex", value = "第几页", required = false, defaultValue = "1", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = false, defaultValue = "20", paramType = "query", dataType = "String")
    })
    public PageResult<AnswerAnd3CommentListDto> list(@RequestParam(name = "questionId", required = true) String questionId,
                                                     @RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                                     @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return answerService.list(questionId, pageIndex, pageSize);
    }

    /**
     * C端-回答详情
     *
     * @return
     */
    @Anonymous
    @GetMapping("/info")
    @ApiOperation(value = "api_answer_info", notes = "回答详情")
    @ApiImplicitParam(name = "id", value = "回答标识", paramType = "query")
    public AnswerInfoDto info(@RequestParam(name = "id", required = false) String id) {
        return answerService.info(id);
    }

    /**
     * C端-下一条回答详情
     *
     * @return
     */
    @Anonymous
    @GetMapping("/next/info")
    @ApiOperation(value = "api_answer_next_info", notes = "下一条回答详情")
    @ApiImplicitParam(name = "lastAnswerId", value = "上一条回答标识", paramType = "query")
    public AnswerInfoDto nextInfo(@RequestParam(name = "lastAnswerId", required = false) String lastAnswerId) {
        return answerService.nextInfo(lastAnswerId);
    }

}
