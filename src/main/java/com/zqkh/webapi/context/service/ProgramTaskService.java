package com.zqkh.webapi.context.service;

import com.zqkh.webapi.context.domain.vo.program.EditGramTaskDoneVo;

/**
 * 方案任务接口
 *
 * @author 东来
 * @create 2018/6/8 0008
 */
public interface ProgramTaskService {

    /**
     * 修改方案任务完成情况
     *
     */
    void editDone(EditGramTaskDoneVo editGramTaskDoneVo);
}
