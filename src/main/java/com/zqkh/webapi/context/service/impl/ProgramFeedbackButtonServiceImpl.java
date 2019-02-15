package com.zqkh.webapi.context.service.impl;

import com.zqkh.healthy.feign.ProgramFeedbackButtonClient;
import com.zqkh.healthy.feign.dto.program.app.ProgramFeedbackButtonListToAppDto;
import com.zqkh.webapi.context.domain.dto.program.ProgramFeedbackButtonListDto;
import com.zqkh.webapi.context.service.ProgramFeedbackButtonService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 方案反馈按钮业务实现层
 *
 * @author 东来
 * @create 2018/7/4 0004
 */
@Service
public class ProgramFeedbackButtonServiceImpl implements ProgramFeedbackButtonService {

    @Resource
    private ProgramFeedbackButtonClient programFeedbackButtonClient;

    @Override
    public List<ProgramFeedbackButtonListDto> all() {
        List<ProgramFeedbackButtonListToAppDto> programFeedbackButtonListToAppDtos = programFeedbackButtonClient.appButtonList();
        if (ObjectUtils.isEmpty(programFeedbackButtonListToAppDtos)) {
            return Collections.EMPTY_LIST;
        }
        return programFeedbackButtonListToAppDtos.stream().map(n -> {
            ProgramFeedbackButtonListDto programFeedbackButtonListDto = ProgramFeedbackButtonListDto.builder()
                    .name(n.getName())
                    .feedback(n.getFeedback())
                    .title(n.getTitle())
                    .type(n.getType())
                    .build();
            return programFeedbackButtonListDto;
        }).collect(Collectors.toList());
    }
}
