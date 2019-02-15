package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.domain.vo.wemedia.question.QuestionVo;
import com.zqkh.wemedia.feign.dto.question.QuestionInfoDto;
import com.zqkh.wemedia.feign.dto.question.QuestionListDto;
import com.zqkh.wemedia.feign.dto.question.UserQuestionListDto;

/**
 * 问题相关接口定义
 *
 * @author 悭梵
 * @date Created in 2018-06-28 15:33
 */
public interface QuestionService {

    /**
     * 提问
     *
     * @param questionVo
     */
    void question(QuestionVo questionVo);

    /**
     * 删除问题
     *
     * @param id
     */
    void delete(String id);

    /**
     * C端 问题、答案及评论信息
     *
     * @param id
     * @return
     */
    QuestionInfoDto info(String id);

    /**
     * C端-我的问题列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageResult<UserQuestionListDto> userList(Integer pageIndex, Integer pageSize);

    /**
     * C端-我的回答列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageResult<QuestionListDto> userAnswerList(Integer pageIndex, Integer pageSize);

    /**
     * C端-问题列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageResult<QuestionListDto> list(int pageIndex, int pageSize);
}
