package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.domain.dto.program.*;
import com.zqkh.webapi.context.domain.vo.program.*;

/**
 * 解决方案业务接口
 *
 * @author 东来
 * @create 2018/6/6 0006
 */
public interface ProgramService {


    /**
     * 搜搜解决方案
     *
     * @param familyMemberId:家庭成员编号
     * @param key:搜索关键字
     * @param done:是否完成
     * @param dayDone:今日是否完成
     * @param programSrcTypeEnum :方案来源类型
     * @param src:方案来源
     * @param pageIndex:第几页
     * @param pageSize:每页显示多少条
     * @return
     */
    PageResult<ProgramListDto> search(String familyMemberId, String key, Boolean done, Boolean dayDone, ProgramSrcTypeEnum programSrcTypeEnum, String src, Integer pageIndex, Integer pageSize);


    /**
     * 方案详情
     *
     * @param id
     * @return
     */
    ProgramInfoDto info(String id);

    /**
     * 方案反馈
     *
     * @param programFeedbackVo
     * @return
     */
    ProgramFeedBackDto feedback(ProgramFeedbackVo programFeedbackVo);

    /**
     * 结束方案
     *
     * @return
     */
    ProgramResultInfoDto end(EndProgramVo endProgramVo);


    /**
     * 查看方案结果详情
     *
     * @param id
     * @return
     */
    ProgramResultInfoDto resultInfo(String id);


    /**
     * 开启方案
     *
     */
    void open(OpenProgramVo openProgramVo);

    /**
     * 拷贝方案
     *
     * @param copyProgramVo
     * @return
     */
    CopyProgramDto copy(CopyProgramVo copyProgramVo);
}
