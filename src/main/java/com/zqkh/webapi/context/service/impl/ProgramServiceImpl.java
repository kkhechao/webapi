package com.zqkh.webapi.context.service.impl;

import com.zqkh.common.result.PageResult;
import com.zqkh.healthy.feign.ProgramClient;
import com.zqkh.healthy.feign.vo.program.ProgramFeedbackTypeEnum;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.domain.dto.program.*;
import com.zqkh.webapi.context.domain.vo.program.*;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.service.ProgramService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 解决方案业务实现层
 *
 * @author 东来
 * @create 2018/6/6 0006
 */
@Service
public class ProgramServiceImpl implements ProgramService {


    @Resource
    private ProgramClient programClient;

    @Override
    public PageResult<ProgramListDto> search(String familyMemberId, String key, Boolean done, Boolean dayDone, ProgramSrcTypeEnum programSrcTypeEnum, String src, Integer pageIndex, Integer pageSize) {

        if (ObjectUtils.isEmpty(familyMemberId)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "请选择一个家庭成员");
        }

        PageResult<com.zqkh.healthy.feign.dto.program.app.ProgramListDto> search = programClient.search(familyMemberId, key, done, dayDone, ObjectUtils.isEmpty(programSrcTypeEnum) ? null : com.zqkh.healthy.feign.vo.program.ProgramSrcTypeEnum.getSrcType(programSrcTypeEnum.name()), src, pageIndex, pageSize);
        PageResult<ProgramListDto> programListDtoPageResult = new PageResult<ProgramListDto>();

        programListDtoPageResult.setPageSize(search.getPageSize());
        programListDtoPageResult.setTotalCount(search.getTotalCount());
        programListDtoPageResult.setList(Collections.EMPTY_LIST);
        if (!ObjectUtils.isEmpty(search.getList())) {
            List<ProgramListDto> list = search.getList().stream().map(n -> {
                ProgramListDto programListDto = new ProgramListDto();
                programListDto.setDayDone(n.getDayDone());
                programListDto.setId(n.getId());
                programListDto.setInsistDay(n.getInsistDay());
                programListDto.setName(n.getName());
                programListDto.setStatus(ProgramListDto.Status.getStatus(n.getStatus().name()));
                programListDto.setProgramResultId(n.getProgramResultId());
                return programListDto;
            }).collect(Collectors.toList());
            programListDtoPageResult.setList(list);
        }
        return programListDtoPageResult;
    }

    @Override
    public ProgramInfoDto info(String id) {

        if (ObjectUtils.isEmpty(id)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "方案编号为空");
        }
        com.zqkh.healthy.feign.dto.program.app.ProgramInfoDto info = programClient.info(id);
        if (ObjectUtils.isEmpty(info)) {
            return null;
        }

        ProgramInfoDto programInfoDto = new ProgramInfoDto();
        programInfoDto.setId(info.getId());
        programInfoDto.setName(info.getName());
        programInfoDto.setTestPaperTitle(info.getTestPaperTitle());
        programInfoDto.setTotalDay(info.getTotalDay());
        programInfoDto.setInsistDay(info.getInsistDay());
        programInfoDto.setStatus(ProgramInfoDto.Status.getStatus(info.getStatus().name()));
        programInfoDto.setProgramResultId(info.getProgramResultId());
        if (!ObjectUtils.isEmpty(info.getTask())) {
            List<ProgramTaskListDto> task = info.getTask().stream().map(n -> {
                ProgramTaskListDto programTaskListDto = new ProgramTaskListDto();
                BeanUtils.copyProperties(n, programTaskListDto);
                return programTaskListDto;
            }).sorted((n1, n2) -> n1.getTime().compareTo(n2.getTime())).collect(Collectors.toList());
            programInfoDto.setTask(task);
        }
        return programInfoDto;
    }

    @Override
    public ProgramFeedBackDto feedback(ProgramFeedbackVo programFeedbackVo) {
        if (ObjectUtils.isEmpty(programFeedbackVo)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "参数为空");
        }
        if (ObjectUtils.isEmpty(programFeedbackVo.getFamilyMemberId())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "家庭成员为空");
        }
        if (ObjectUtils.isEmpty(programFeedbackVo.getProgramId())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "方案编号为空");
        }
        if (ObjectUtils.isEmpty(programFeedbackVo.getTime())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "反馈日期为空");
        }
        if (ObjectUtils.isEmpty(programFeedbackVo.getType())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "反馈类型为空");
        }

        Date time = new Date(programFeedbackVo.getTime());

        if (ObjectUtils.isEmpty(time)) {
            time = new Date();
        }

        com.zqkh.healthy.feign.vo.program.ProgramFeedbackVo programFeedbackVo1 = new com.zqkh.healthy.feign.vo.program.ProgramFeedbackVo();
        programFeedbackVo1.setTime(time);
        programFeedbackVo1.setFamilyMemberId(programFeedbackVo.getFamilyMemberId());
        programFeedbackVo1.setProgramId(programFeedbackVo.getProgramId());
        programFeedbackVo1.setType(ProgramFeedbackTypeEnum.getType(programFeedbackVo.getType().name()));

        String message = programClient.feedback(programFeedbackVo1);

        return ProgramFeedBackDto.builder().message(message).build();
    }

    @Override
    public ProgramResultInfoDto end(EndProgramVo endProgramVo) {
        if (ObjectUtils.isEmpty(endProgramVo)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "参数为空");
        }
        if (ObjectUtils.isEmpty(endProgramVo.getId())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "方案编号为空");
        }
        if (ObjectUtils.isEmpty(endProgramVo.getFamilyMemberId())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "家庭成员为空");
        }


        com.zqkh.healthy.feign.dto.program.app.ProgramResultInfoDto programResultInfoDto = programClient.endProgram(endProgramVo.getId(), endProgramVo.getFamilyMemberId());
        if (ObjectUtils.isEmpty(programResultInfoDto)) {
            throw new BusinessException(BusinessExceptionEnum.RESULT_IS_NULL.getCode(), "解决方案结果异常");
        }

        ProgramResultInfoDto programResultInfoDto1 = new ProgramResultInfoDto();
        BeanUtils.copyProperties(programResultInfoDto, programResultInfoDto1);
        if (programResultInfoDto1.getTotal() < 1000) {
            programResultInfoDto1.setTotal(programResultInfoDto1.getTotal() * 100);
        }
        if (programResultInfoDto1.getTranscend() < 1000) {
            programResultInfoDto1.setTotal(programResultInfoDto1.getTranscend() * 100);
        }

        return programResultInfoDto1;
    }

    @Override
    public ProgramResultInfoDto resultInfo(String id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "方案结果编号为空");
        }
        com.zqkh.healthy.feign.dto.program.app.ProgramResultInfoDto programResultInfo = programClient.getProgramResultInfo(id);

        ProgramResultInfoDto programResultInfoDto = new ProgramResultInfoDto();
        BeanUtils.copyProperties(programResultInfo, programResultInfoDto);
        if (programResultInfoDto.getTotal() < 1000) {
            programResultInfoDto.setTotal(programResultInfoDto.getTotal() * 100);
        }
        if (programResultInfoDto.getTranscend() < 1000) {
            programResultInfoDto.setTotal(programResultInfoDto.getTranscend() * 100);
        }
        return programResultInfoDto;
    }

    @Override
    public void open(OpenProgramVo openProgramVo) {
        if (ObjectUtils.isEmpty(openProgramVo)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "参数为空");
        }
        if (ObjectUtils.isEmpty(openProgramVo.getId())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "方案编号为空");
        }
        if (ObjectUtils.isEmpty(openProgramVo.getFamilyMemberId())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "家庭成员为空");
        }
        programClient.open(openProgramVo.getId(), openProgramVo.getFamilyMemberId());
    }

    @Override
    public CopyProgramDto copy(CopyProgramVo copyProgramVo) {
        if (ObjectUtils.isEmpty(copyProgramVo)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "参数为空");
        }
        if (ObjectUtils.isEmpty(copyProgramVo.getId())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "方案编号为空");
        }
        if (ObjectUtils.isEmpty(copyProgramVo.getFamilyMemberId())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "家庭成员为空");
        }

        String id = programClient.copy(copyProgramVo.getId(), copyProgramVo.getFamilyMemberId());
        CopyProgramDto copyProgramDto = new CopyProgramDto();
        copyProgramDto.setId(id);
        return copyProgramDto;
    }
}
