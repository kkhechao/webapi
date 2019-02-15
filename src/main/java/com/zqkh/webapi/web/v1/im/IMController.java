package com.zqkh.webapi.web.v1.im;

import com.zqkh.common.result.PageResult;
import com.zqkh.im.feign.dto.conversation.MyConversationDto;
import com.zqkh.im.feign.dto.message.MessageDto;
import com.zqkh.webapi.context.service.IMService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * IM controller
 * Created by wenjie on 2018/5/16 0016.
 */
@RestController
@RequestMapping("/im/")
@Slf4j
public class IMController {

    @Autowired
    private IMService imService;

    /**
     * 我的会话列表
     *
     * @return
     */
    @GetMapping("/conversations")
    @ApiOperation(value = "api_im_conversations")
    private List<MyConversationDto> getMyConversations() {
        return imService.getMyConversations();
    }

    /**
     * 读取会话中的消息
     *
     * @param conversationId
     * @return
     */
    @GetMapping("/message")
    @ApiOperation(value = "api_im_conversationMessage")
    public PageResult<MessageDto> getMessageByConversation(String conversationId, Integer pageIndex, Integer pageSize) {
        return imService.getMyMessageOnConversation(conversationId, pageIndex, pageSize);
    }

}
