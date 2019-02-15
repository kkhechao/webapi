package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.im.feign.dto.conversation.MyConversationDto;
import com.zqkh.im.feign.dto.message.MessageDto;

import java.util.List;

/**
 * IM service
 * Created by wenjie on 2018/5/16 0016.
 */
public interface IMService {
    List<MyConversationDto> getMyConversations();

    PageResult<MessageDto> getMyMessageOnConversation(String conversationId, Integer pageIndex, Integer pageSize);
}
