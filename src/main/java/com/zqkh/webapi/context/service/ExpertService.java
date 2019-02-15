package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.domain.vo.wemedia.expert.InvitationAnswerQuestionVo;
import com.zqkh.wemedia.feign.dto.expert.ExpertDto;

/**
 * 专家列表及邀请专家
 *
 * @author 悭梵
 * @date Created in 2018-07-03 10:09
 */
public interface ExpertService {


    /**
     * 可邀请专家列表
     *
     * @param questionId
     * @param typeId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageResult<ExpertDto> list(String questionId, String typeId, int pageIndex, int pageSize);

    /**
     * 邀请专家回答问题
     *
     * @param invitationAnswerQuestionVo
     */
    void invitationAnswerQuestion(InvitationAnswerQuestionVo invitationAnswerQuestionVo);
}
