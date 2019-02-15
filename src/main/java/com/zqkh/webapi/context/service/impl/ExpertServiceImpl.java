package com.zqkh.webapi.context.service.impl;

import com.zqkh.common.exception.BusinessException;
import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.domain.vo.wemedia.expert.InvitationAnswerQuestionVo;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.ExpertService;
import com.zqkh.wemedia.feign.ExpertFeignClient;
import com.zqkh.wemedia.feign.dto.expert.ExpertDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 专家列表及邀请专家业务实现
 *
 * @author 悭梵
 * @date Created in 2018-07-03 10:10
 */
@Service
public class ExpertServiceImpl implements ExpertService {

    @Resource
    private ExpertFeignClient expertFeignClient;

    @Override
    public PageResult<ExpertDto> list(String questionId, String typeId, int pageIndex, int pageSize) {
        JWTUserDto userDto = AuthManager.currentUser();
        return expertFeignClient.list(questionId, typeId, userDto == null ? null : userDto.getUserId(), pageIndex, pageSize);
    }

    @Override
    public void invitationAnswerQuestion(InvitationAnswerQuestionVo invitationAnswerQuestionVo) {
        if (invitationAnswerQuestionVo == null) {
            throw new BusinessException("参数为空", null);
        }
        if (StringUtils.isBlank(invitationAnswerQuestionVo.getQuestionId())) {
            throw new BusinessException("问题标识为空", null);
        }
        if (StringUtils.isBlank(invitationAnswerQuestionVo.getExpertId())) {
            throw new BusinessException("专家标识为空", null);
        }
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            throw new com.zqkh.webapi.context.exception.BusinessException(BusinessExceptionEnum.LOGIN_FAIL.getCode(), "请登录");
        }
        // feign接口调用
        com.zqkh.wemedia.feign.vo.InvitationAnswerQuestionVo feignInvitationAnswerQuestionVo = new com.zqkh.wemedia.feign.vo.InvitationAnswerQuestionVo();
        feignInvitationAnswerQuestionVo.setPeopleId(userDto.getUserId());
        feignInvitationAnswerQuestionVo.setQuestionId(invitationAnswerQuestionVo.getQuestionId());
        feignInvitationAnswerQuestionVo.setExpertIdList(Arrays.asList(invitationAnswerQuestionVo.getExpertId()));
        expertFeignClient.invitationAnswerQuestion(feignInvitationAnswerQuestionVo);
    }
}
