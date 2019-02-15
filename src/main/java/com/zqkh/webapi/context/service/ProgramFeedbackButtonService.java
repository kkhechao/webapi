package com.zqkh.webapi.context.service;

import com.zqkh.webapi.context.domain.dto.program.ProgramFeedbackButtonListDto;

import java.util.List;

/**
 * 方案反馈按钮业务接口
 *
 * @author 东来
 * @create 2018/7/4 0004
 */
public interface ProgramFeedbackButtonService {


    /**
     * 获取所有方案反馈按钮列表
     *
     * @return
     */
    List<ProgramFeedbackButtonListDto> all();
}
