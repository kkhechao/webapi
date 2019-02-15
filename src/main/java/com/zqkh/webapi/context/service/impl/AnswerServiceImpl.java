package com.zqkh.webapi.context.service.impl;

import com.zqkh.common.result.PageResult;
import com.zqkh.system.sdk.annotation.FilterKeywords;
import com.zqkh.system.sdk.annotation.SystemSdk;
import com.zqkh.user.feign.UserClient;
import com.zqkh.user.feign.dto.UserDto;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.domain.vo.wemedia.answer.AnswerVo;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.AnswerService;
import com.zqkh.wemedia.feign.AnswerFeignClient;
import com.zqkh.wemedia.feign.dto.answer.AnswerAnd3CommentListDto;
import com.zqkh.wemedia.feign.dto.answer.AnswerInfoDto;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * 回答业务实现
 *
 * @author 悭梵
 * @date Created in 2018-06-28 15:59
 */
@Log4j
@Service
public class AnswerServiceImpl implements AnswerService {

    @Resource
    private UserClient userClient;
    @Resource
    private AnswerFeignClient answerFeignClient;

    @Override
    @SystemSdk
    public void answer(@FilterKeywords(name = "content") AnswerVo answerVo) {
        if (answerVo == null) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "参数为空");
        }
        if (StringUtils.isBlank(answerVo.getQuestionId())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "回答问题标识为空");
        }
        if (StringUtils.isBlank(answerVo.getContent())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "回答内容为空");
        }
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BusinessException(BusinessExceptionEnum.LOGIN_FAIL.getCode(), "请登录");
        }
        com.zqkh.wemedia.feign.vo.AnswerVo feignAnswerVo = new com.zqkh.wemedia.feign.vo.AnswerVo();
        feignAnswerVo.setQuestionId(answerVo.getQuestionId());
        feignAnswerVo.setContent(answerVo.getContent());
        feignAnswerVo.setPeopleId(userDto.getUserId());
        if (StringUtils.isBlank(userDto.getNickName()) || StringUtils.isBlank(userDto.getAvatar()) || StringUtils.isBlank(userDto.getMobile())) {
            try {
                UserDto userInfo = userClient.getUserInfo(userDto.getUserId());
                feignAnswerVo.setPhone(userInfo.getPhone());
                feignAnswerVo.setNickName(userInfo.getNickName());
                feignAnswerVo.setHeadUrl(userInfo.getAvatar());
            } catch (RuntimeException e) {
                log.error("用户失败", e);
            }
        } else {
            feignAnswerVo.setPhone(userDto.getMobile());
            feignAnswerVo.setNickName(userDto.getNickName());
            feignAnswerVo.setHeadUrl(userDto.getAvatar());
        }
        answerFeignClient.answer(feignAnswerVo);
    }

    @Override
    public void delete(String id) {
        if (StringUtils.isBlank(id)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "答案标识为空");
        }
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BusinessException(BusinessExceptionEnum.LOGIN_FAIL.getCode(), "请登录");
        }
        AnswerInfoDto answerInfoDto = answerFeignClient.cInfo(id, false, null);
        if (answerInfoDto == null) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "该回答不存在或已删除");
        }
        if (StringUtils.isNotBlank(answerInfoDto.getPeopleId()) && answerInfoDto.getPeopleId().equals(userDto.getUserId())) {
            answerFeignClient.delete(id);
        } else {
            throw new BusinessException(BusinessExceptionEnum.INVALID_PARAMS.getCode(), "你不能删除其他人的回答");
        }
    }

    @Override
    public void applaud(String id) {
        if (StringUtils.isBlank(id)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "答案标识为空");
        }
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BusinessException(BusinessExceptionEnum.LOGIN_FAIL.getCode(), "请登录");
        }
        answerFeignClient.applaud(id, userDto.getUserId());
    }

    @Override
    public void belittle(String id) {
        if (StringUtils.isBlank(id)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "答案标识为空");
        }
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BusinessException(BusinessExceptionEnum.LOGIN_FAIL.getCode(), "请登录");
        }
        answerFeignClient.belittle(id, userDto.getUserId());
    }

    @Override
    public AnswerInfoDto info(String id) {
        if (StringUtils.isBlank(id)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "答案标识为空");
        }
        JWTUserDto userDto = AuthManager.currentUser();
        return answerFeignClient.cInfo(id, false, userDto == null ? null : userDto.getUserId());
    }

    @Override
    public AnswerInfoDto nextInfo(String lastAnswerId) {
        if (StringUtils.isBlank(lastAnswerId)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "答案标识为空");
        }
        JWTUserDto userDto = AuthManager.currentUser();
        return answerFeignClient.cInfo(lastAnswerId, true, userDto == null ? null : userDto.getUserId());
    }

    @Override
    public PageResult<AnswerAnd3CommentListDto> list(String questionId, int pageIndex, int pageSize) {
        if (StringUtils.isBlank(questionId)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "对应问题标识为空");
        }
        JWTUserDto userDto = AuthManager.currentUser();
        return answerFeignClient.cList(questionId, userDto == null ? null : userDto.getUserId(), pageIndex, pageSize);
    }
}
