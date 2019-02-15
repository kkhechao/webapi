package com.zqkh.webapi.context.service.impl;

import com.zqkh.common.result.PageResult;
import com.zqkh.im.feign.ConversationClient;
import com.zqkh.im.feign.dto.conversation.MyConversationDto;
import com.zqkh.im.feign.dto.message.MessageDto;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.IMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * Created by wenjie on 2018/5/16 0016.
 */
@Service
public class IMServiceImpl implements IMService {

    @Autowired
    private ConversationClient conversationClient;


    @Override
    public List<MyConversationDto> getMyConversations() {
        JWTUserDto jwtUserDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(jwtUserDto)) {
            throw new BusinessException(BusinessExceptionEnum.LOGIN_FAIL.getCode(), "请登录");
        }
        return conversationClient.myConversation(jwtUserDto.getUserId());
    }

    @Override
    public PageResult<MessageDto> getMyMessageOnConversation(String conversationId, Integer pageIndex, Integer pageSize) {
        return conversationClient.conversationMessage(conversationId, pageIndex, pageSize);
    }
}
