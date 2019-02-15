package com.zqkh.webapi.context.service.impl;

import com.zqkh.file.dto.ResourceInfoDto;
import com.zqkh.file.feign.FileFeign;
import com.zqkh.healthy.feign.ProgramActionClient;
import com.zqkh.healthy.feign.dto.program.app.ProgramActionDto;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.dto.healthy.program.SelectProgramActionDto;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.service.ProgramActionService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 查看解决方案接口实现
 *
 * @author 东来
 * @create 2018/5/15 0015
 */
@Service
public class ProgramActionServiceImpl implements ProgramActionService {

    @Resource
    private ProgramActionClient programActionClient;

    @Resource
    private FileFeign fileFeign;

    @Override
    public List<SelectProgramActionDto> selectProgramAction(String familyMemberId) {
        if (ObjectUtils.isEmpty(familyMemberId)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "请选择一个家庭成员");
        }

        List<ProgramActionDto> programActionDtoList = programActionClient.selectProgramAction(familyMemberId);
        if (ObjectUtils.isEmpty(programActionDtoList)) {
            return Collections.EMPTY_LIST;
        }


        Set<String> resourceSet = new HashSet<>();

        List<String> resourceId = programActionDtoList.stream().map(n -> n.getCoverId()).collect(Collectors.toList());

        resourceSet.addAll(resourceId);

        List<ResourceInfoDto> resourceInfoDtoList = fileFeign.getResourceInfo(resourceSet.stream().collect(Collectors.toList()));


        Map<String, String> resourceMap = new HashMap<>();
        if (!ObjectUtils.isEmpty(resourceInfoDtoList)) {
            resourceMap = resourceInfoDtoList.stream().collect(Collectors.toMap(ResourceInfoDto::getId, ResourceInfoDto::getUrl));
        }
        Map<String, String> urlMap = resourceMap;

        List<SelectProgramActionDto> result = programActionDtoList.stream().map(n -> {
            SelectProgramActionDto selectProgramActionDto = new SelectProgramActionDto();
            selectProgramActionDto.setActionContent(n.getActionContent());
            selectProgramActionDto.setId(n.getId());
            selectProgramActionDto.setPointName(n.getPointName());
            selectProgramActionDto.setResult(n.getResult());
            selectProgramActionDto.setTitle(n.getTitle());
            selectProgramActionDto.setCoverUrl(ObjectUtils.isEmpty(n.getCoverId()) ? null : urlMap.get(n.getCoverId()));
            return selectProgramActionDto;
        }).collect(Collectors.toList());


        return result;
    }
}
