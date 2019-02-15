package com.zqkh.webapi.web.v1.wemedia;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.domain.vo.wemedia.expert.InvitationAnswerQuestionVo;
import com.zqkh.webapi.context.service.ExpertService;
import com.zqkh.wemedia.feign.dto.expert.ExpertDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 专家列表及邀请专家接口
 *
 * @author 悭梵
 * @date Created in 2018-07-03 10:07
 */
@RestController
@RequestMapping("/me/media/expert")
public class ExpertController {

    @Resource
    private ExpertService expertService;

    /**
     * 可邀请专家列表
     *
     * @param typeId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Anonymous
    @GetMapping("/invitation/answer/question/{questionId}")
    @ApiOperation(value = "api_invitation_expert_list", notes = "可邀请专家列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "专家分类标识", paramType = "query"),
            @ApiImplicitParam(name = "questionId", value = "专家分类标识", paramType = "path"),
            @ApiImplicitParam(name = "pageIndex", value = "第几页", required = false, defaultValue = "1", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = false, defaultValue = "20", paramType = "query", dataType = "String")
    })
    public PageResult<ExpertDto> list(@PathVariable("questionId") String questionId,
                                      @RequestParam(name = "typeId", required = false) String typeId,
                                      @RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return expertService.list(questionId, typeId, pageIndex, pageSize);
    }

    /**
     * 邀请专家回答问题
     *
     * @param invitationAnswerQuestionVo
     */
    @PostMapping("/invitation/answer/question")
    @ApiOperation(value = "api_invitation_expert_answer_question", notes = "邀请专家回答问题")
    public void invitationAnswerQuestion(@RequestBody InvitationAnswerQuestionVo invitationAnswerQuestionVo) {
        expertService.invitationAnswerQuestion(invitationAnswerQuestionVo);
    }

}
