package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.domain.vo.wemedia.answer.AnswerVo;
import com.zqkh.wemedia.feign.dto.answer.AnswerAnd3CommentListDto;
import com.zqkh.wemedia.feign.dto.answer.AnswerInfoDto;

/**
 * 回答接口
 *
 * @author 悭梵
 * @date Created in 2018-06-28 15:59
 */
public interface AnswerService {

    /**
     * 回答问题
     *
     * @param answerVo
     */
    void answer(AnswerVo answerVo);

    /**
     * 删除回答
     *
     * @param id
     */
    void delete(String id);

    /**
     * 点赞
     *
     * @param id
     */
    void applaud(String id);

    /**
     * 贬低
     *
     * @param id
     */
    void belittle(String id);

    /**
     * 回答详情
     *
     * @param id
     * @return
     */
    AnswerInfoDto info(String id);

    /**
     * 下一条回答详情
     *
     * @param lastAnswerId
     * @return
     */
    AnswerInfoDto nextInfo(String lastAnswerId);

    /**
     * C端-分页查询问题回答列表
     *
     * @param questionId 问题标识
     * @param pageIndex
     * @param pageSize
     * @info
     */
    PageResult<AnswerAnd3CommentListDto> list(String questionId, int pageIndex, int pageSize);
}
