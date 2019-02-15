package com.zqkh.webapi.context.service.impl;

import com.zqkh.common.result.PageResult;
import com.zqkh.system.sdk.annotation.FilterKeywords;
import com.zqkh.system.sdk.annotation.SystemSdk;
import com.zqkh.user.feign.UserClient;
import com.zqkh.user.feign.dto.UserDto;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.domain.vo.wemedia.question.QuestionVo;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.QuestionService;
import com.zqkh.wemedia.feign.QuestionFeginClient;
import com.zqkh.wemedia.feign.dto.question.QuestionInfoDto;
import com.zqkh.wemedia.feign.dto.question.QuestionListDto;
import com.zqkh.wemedia.feign.dto.question.UserQuestionListDto;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * 问题业务层实现
 *
 * @author 悭梵
 * @date Created in 2018-06-28 15:33
 */
@Log4j
@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionFeginClient questionFeginClient;
    @Resource
    private UserClient userClient;


    @Override
    @SystemSdk
    public void question(@FilterKeywords(name = "content")
                         @FilterKeywords(name = "description") QuestionVo questionVo) {
        if (questionVo == null) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "参数为空");
        }
        if (StringUtils.isBlank(questionVo.getContent())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "问题内容为空");
        }
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BusinessException(BusinessExceptionEnum.LOGIN_FAIL.getCode(), "请登录");
        }

        com.zqkh.wemedia.feign.vo.QuestionVo feignQuestionVo = new com.zqkh.wemedia.feign.vo.QuestionVo();
        feignQuestionVo.setContent(questionVo.getContent());
        feignQuestionVo.setDescription(questionVo.getDescription());
        feignQuestionVo.setFileUrlList(questionVo.getFileUrlList());
        feignQuestionVo.setPeopleId(userDto.getUserId());
        if (StringUtils.isBlank(userDto.getNickName()) || StringUtils.isBlank(userDto.getAvatar()) || StringUtils.isBlank(userDto.getMobile())) {
            try {
                UserDto userInfo = userClient.getUserInfo(userDto.getUserId());
                feignQuestionVo.setNickName(userInfo.getNickName());
                feignQuestionVo.setHeadUrl(userInfo.getAvatar());
                feignQuestionVo.setPhone(userInfo.getPhone());
            } catch (RuntimeException e) {
                log.error("用户失败", e);
            }
        } else {
            feignQuestionVo.setNickName(userDto.getNickName());
            feignQuestionVo.setHeadUrl(userDto.getAvatar());
            feignQuestionVo.setPhone(userDto.getMobile());
        }
        questionFeginClient.question(feignQuestionVo);
    }

    @Override
    public void delete(String id) {
        if (StringUtils.isBlank(id)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "问题标识为空");
        }
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BusinessException(BusinessExceptionEnum.LOGIN_FAIL.getCode(), "请登录");
        }
        QuestionInfoDto questionInfoDto = info(id);
        if (questionInfoDto == null) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "该问题不存在或已删除");
        }
        if (StringUtils.isNotBlank(questionInfoDto.getPeopleId()) && questionInfoDto.getPeopleId().equals(userDto.getUserId())) {
            questionFeginClient.delete(id);
        } else {
            throw new BusinessException(BusinessExceptionEnum.INVALID_PARAMS.getCode(), "你不能删除其他人提出的问题");
        }
    }

    @Override
    public QuestionInfoDto info(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        return questionFeginClient.cInfo(id);
    }

    @Override
    public PageResult<UserQuestionListDto> userList(Integer pageIndex, Integer pageSize) {
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BusinessException(BusinessExceptionEnum.LOGIN_FAIL.getCode(), "请登录");
        }
        return questionFeginClient.cUserList(userDto.getUserId(), pageIndex, pageSize);
    }

    @Override
    public PageResult<QuestionListDto> userAnswerList(Integer pageIndex, Integer pageSize) {
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BusinessException(BusinessExceptionEnum.LOGIN_FAIL.getCode(), "请登录");
        }
        return questionFeginClient.cUserAnswerList(userDto.getUserId(), pageIndex, pageSize);
    }

    @Override
    public PageResult<QuestionListDto> list(int pageIndex, int pageSize) {
        return questionFeginClient.cList(pageIndex, pageSize);
    }
}
