package com.zqkh.webapi.context.service.impl;

import com.zqkh.healthy.feign.ProgramTaskClient;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.domain.vo.program.EditGramTaskDoneVo;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.service.ProgramTaskService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * 方案任务接口实现
 *
 * @author 东来
 * @create 2018/6/8 0008
 */
@Service
public class ProgramTaskServiceImpl implements ProgramTaskService {

    @Resource
    private ProgramTaskClient programTaskClient;

    @Override
    public void editDone(EditGramTaskDoneVo editGramTaskDoneVo) {
        if (ObjectUtils.isEmpty(editGramTaskDoneVo)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "参数为空");
        }
        if (ObjectUtils.isEmpty(editGramTaskDoneVo.getFamilyMemberId())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "家庭成员为空");
        }
        if (ObjectUtils.isEmpty(editGramTaskDoneVo.getId())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "任务编号为空");
        }
        if (ObjectUtils.isEmpty(editGramTaskDoneVo.getDone())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "是否完成为空");
        }
        programTaskClient.editDone(editGramTaskDoneVo.getId(), editGramTaskDoneVo.getFamilyMemberId(), editGramTaskDoneVo.getDone());

    }
}
