package com.zqkh.webapi.context.service;


import com.zqkh.webapi.context.domain.vo.test.paper.SubmitAnAnswerVo;
import com.zqkh.webapi.context.dto.healthy.test.paper.TestPaperInfoDto;
import com.zqkh.webapi.context.dto.healthy.test.paper.TestPaperListDto;
import com.zqkh.webapi.context.dto.healthy.test.paper.TestResultDto;

/**
 * 试卷业务接口
 *
 * @author 东来
 * @create 2018/5/9 0009
 */
public interface TestPaperService {


    /**
     * 试卷列表
     *
     * @param familyMemberId
     * @return
     */
    TestPaperListDto getTestPaperList(String familyMemberId, String type);


    /**
     * 试卷详情
     *
     * @param id
     * @param familyMemberId
     * @return
     */
    TestPaperInfoDto getTestPaperInfo(String id, String familyMemberId);


    /**
     * 获取试卷答题结果
     *
     * @param id:答题结果编号
     * @return
     */
    TestResultDto getTestResult(String id);


    /**
     * 提交试卷答案
     *
     * @param submitAnAnswerVo
     */
    TestResultDto submitAnAnswer(SubmitAnAnswerVo submitAnAnswerVo);


}
