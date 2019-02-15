package com.zqkh.webapi.context.service.impl;

import com.zqkh.common.result.PageResult;
import com.zqkh.system.feign.SettingFeignClient;
import com.zqkh.system.feign.dto.SettingDto;
import com.zqkh.system.sdk.annotation.FilterKeywords;
import com.zqkh.system.sdk.annotation.SystemSdk;
import com.zqkh.user.feign.UserClient;
import com.zqkh.user.feign.dto.UserDto;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.dto.wemedia.comment.CommentDto;
import com.zqkh.webapi.context.dto.wemedia.comment.CommentReplyDto;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.CommentService;
import com.zqkh.wemedia.feign.CommentFeignClient;
import com.zqkh.wemedia.feign.vo.CommentReplyVo;
import com.zqkh.wemedia.feign.vo.CommentVo;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 评论WEB API 业务层实现
 *
 * @author 悭梵
 * @date Created in 2018-05-30 14:38
 */
@Log4j
@Service
public class CommentServiceImpl implements CommentService {
    /**
     * 总开关，用于控制前端是否现在评论，默认显示
     */
    private static final String WEMEDIA_SHOW_COMMENT = "WEMEDIA_SHOW_COMMENT";
    /**
     * 总开关，用于控制是否允许评论，默认允许
     */
    private static final String WEMEDIA_ALLOW_COMMENT = "WEMEDIA_ALLOW_COMMENT";
    /**
     * 总开关，用于控制用户评论频率，防止刷评论，默认5秒
     */
    private static final String WEMEDIA_USER_COMMENT_FREQUENCY = "WEMEDIA_USER_COMMENT_FREQUENCY";
    /**
     * 总开关，用于控制同一IP地址在1小时内最大评论数量，防止刷评论，默认100条
     */
    private static final String WEMEDIA_IP_COMMENT_ONE_HOUR_AMOUNT = "WEMEDIA_IP_COMMENT_ONE_HOUR_AMOUNT";
    /**
     * 用户单位时间内评论数量
     */
    private static final String CACHE_NAME_USER_COMMENT_NUM = "user_comment_x_seconds_";
    /**
     * IP1小时内评论数量
     */
    private static final String CACHE_NAME_IP_COMMENT_NUM = "ip_comment_one_hour_num_";

    @Resource
    private CommentFeignClient commentFeignClient;
    @Resource
    private SettingFeignClient settingFeignClient;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UserClient userClient;

    @Override
    @SystemSdk
    public void comment(String objectId, String type, @FilterKeywords String context, String ip) {
        if (StringUtils.isBlank(objectId)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "评论对象标识为空");
        }
        if (StringUtils.isBlank(type)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "评论对象类型为空");
        } else if (CommentVo.Type.valueOf(type) == null) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "评论对象类型不存在");
        }
        if (StringUtils.isBlank(context)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "评论内容为空");
        }

        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BusinessException(BusinessExceptionEnum.LOGIN_FAIL.getCode(), "请登录");
        }

        commentFrequencyLimit(userDto.getUserId(), ip);

        CommentVo commentVo = new CommentVo();
        commentVo.setObjectId(objectId);
        commentVo.setType(CommentVo.Type.valueOf(type));
        commentVo.setContext(context);
        commentVo.setPeopleId(userDto.getId());
        if (StringUtils.isBlank(userDto.getNickName())) {
            try {
                UserDto user = userClient.getUserInfo(userDto.getId());
                commentVo.setHeadUrl(user.getAvatar());
                commentVo.setNickName(user.getNickName());
            } catch (RuntimeException e) {
                log.error("用户失败", e);
            }
        } else {
            commentVo.setHeadUrl(userDto.getAvatar());
            commentVo.setNickName(userDto.getNickName());
        }
        commentFeignClient.comment(commentVo);
    }

    @Override
    @SystemSdk
    public void commentReply(String commentId, @FilterKeywords String context, String ip) {
        if (StringUtils.isBlank(commentId)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "被回复的评论标识为空");
        }
        if (ObjectUtils.isEmpty(context)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "评论内容为空");
        }
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BusinessException(BusinessExceptionEnum.LOGIN_FAIL.getCode(), "请登录");
        }

        commentFrequencyLimit(userDto.getUserId(), ip);

        CommentReplyVo commentReplyVo = new CommentReplyVo();
        commentReplyVo.setCommentId(commentId);
        commentReplyVo.setContext(context);
        commentReplyVo.setPeopleId(userDto.getId());

        if (StringUtils.isBlank(userDto.getNickName())) {
            try {
                UserDto user = userClient.getUserInfo(userDto.getId());
                commentReplyVo.setHeadUrl(user.getAvatar());
                commentReplyVo.setNickName(user.getNickName());
            } catch (RuntimeException e) {
                log.error("用户失败", e);
            }
        } else {
            commentReplyVo.setHeadUrl(userDto.getAvatar());
            commentReplyVo.setNickName(userDto.getNickName());
        }

        commentFeignClient.commentReply(commentReplyVo);
    }

    @Override
    public void applaud(String commentId) {
        if (StringUtils.isBlank(commentId)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "被点赞的评论标识为空");
        }
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BusinessException(BusinessExceptionEnum.LOGIN_FAIL.getCode(), "请登录");
        }
        commentFeignClient.applaud(commentId, userDto.getUserId());
    }

    @Override
    public PageResult<CommentDto> list(String articleId, int pageIndex, int pageSize) {
        return commentObjectList(articleId, "ARTICLE", pageIndex, pageSize);
    }

    @Override
    public PageResult<CommentDto> commentObjectList(String objectId, String objectType, int pageIndex, int pageSize) {
        String appId = CommentFeignClient.class.getAnnotation(FeignClient.class).value();
        SettingDto settingDto = settingFeignClient.info(appId, WEMEDIA_SHOW_COMMENT);
        if (settingDto != null && StringUtils.isNotBlank(settingDto.getValue())) {
            if (!BooleanUtils.toBoolean(settingDto.getValue())) {
                return new PageResult<CommentDto>(0, pageSize, Collections.emptyList());
            }
        }

        if (ObjectUtils.isEmpty(objectId)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "评论对象标识为空");
        }
        if (ObjectUtils.isEmpty(objectType)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "评论对象类型为空");
        }
        if (!"ARTICLE".equals(objectType) && !"ANSWER".equals(objectType)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "评论对象类型错误");
        }
        JWTUserDto userDto = AuthManager.currentUser();
        PageResult<com.zqkh.wemedia.feign.dto.comment.CommentDto> feignCommentList = commentFeignClient.listCommentByObjectIdAndType(objectType, objectId, userDto == null ? "" : userDto.getUserId(), pageIndex, pageSize);
        if (feignCommentList.getList() == null || feignCommentList.getList().isEmpty()) {
            return new PageResult<CommentDto>(feignCommentList.getTotalCount(), feignCommentList.getPageSize(), Collections.EMPTY_LIST);
        }
        return new PageResult<CommentDto>(feignCommentList.getTotalCount(), feignCommentList.getPageSize(), feignCommentList.getList().stream().map(feignCommentDto -> {
            CommentDto commentDto = new CommentDto();
            convertFeignCommentDtoToCommentDto(feignCommentDto, commentDto);
            return commentDto;
        }).collect(Collectors.toList()));
    }

    /**
     * 将feign的CommentDto转变为WEB API的CommentDto
     *
     * @param feignCommentDto
     * @param commentDto
     */
    private void convertFeignCommentDtoToCommentDto(com.zqkh.wemedia.feign.dto.comment.CommentDto feignCommentDto, CommentDto commentDto) {
        if (feignCommentDto == null || commentDto == null) {
            return;
        }
        commentDto.setId(feignCommentDto.getId());
        commentDto.setPeopleId(feignCommentDto.getPeopleId());

        if (StringUtils.isBlank(feignCommentDto.getNickName())) {
            UserDto user = userClient.getUserInfo(feignCommentDto.getPeopleId());
            commentDto.setHeadUrl(user.getAvatar());
            commentDto.setNickName(user.getNickName());
        } else {
            commentDto.setHeadUrl(feignCommentDto.getHeadUrl());
            commentDto.setNickName(feignCommentDto.getNickName());
        }
        commentDto.setApplaudNum(feignCommentDto.getApplaudNum());
        commentDto.setStatus(feignCommentDto.getStatus());
        commentDto.setCreateTime(feignCommentDto.getCreateTime());
        commentDto.setContext(feignCommentDto.getStatus() == 0 ? null : feignCommentDto.getContext());
        commentDto.setReplyCount(feignCommentDto.getReplyCount());
        commentDto.setApplaud(feignCommentDto.isApplaud());
        commentDto.setReplyList(convertFeignCommentReplyDtoList(feignCommentDto.getReplyList()));
    }

    /**
     * 评论回复树转换
     *
     * @param feginReplyList
     * @return
     */
    private List<CommentReplyDto> convertFeignCommentReplyDtoList(List<com.zqkh.wemedia.feign.dto.comment.CommentReplyDto> feginReplyList) {
        if (feginReplyList == null || feginReplyList.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return feginReplyList.stream().map(feginReplyDto -> {
            CommentReplyDto replyDto = new CommentReplyDto();
            convertFeignCommentDtoToCommentDto(feginReplyDto, replyDto);
            replyDto.setReplyPeopleId(feginReplyDto.getPeopleId());
            replyDto.setReplyHeadUrl(feginReplyDto.getHeadUrl());
            replyDto.setReplyNickName(feginReplyDto.getNickName());
            return replyDto;
        }).collect(Collectors.toList());
    }


    /**
     * 评论频次限制
     *
     * @param ip
     * @param userId
     */
    private void commentFrequencyLimit(String userId, String ip) {
        String appId = CommentFeignClient.class.getAnnotation(FeignClient.class).value();
        SettingDto settingDto = settingFeignClient.info(appId, WEMEDIA_ALLOW_COMMENT);
        if (settingDto != null && StringUtils.isNotBlank(settingDto.getValue())) {
            if (!BooleanUtils.toBoolean(settingDto.getValue())) {
                throw new BusinessException(BusinessExceptionEnum.SYSTEM_SETTING_FAAL.getCode(), "管理员暂时关闭了评论功能");
            }
        }

        ValueOperations valueOperations = redisTemplate.opsForValue();
        if (StringUtils.isNotBlank(userId)) {
            settingDto = settingFeignClient.info(appId, WEMEDIA_USER_COMMENT_FREQUENCY);
            if (settingDto != null && NumberUtils.isNumber(settingDto.getValue())) {
                String cacheKey = CACHE_NAME_USER_COMMENT_NUM + userId;
                long num = valueOperations.increment(cacheKey, 1);
                if (num == 1) {
                    redisTemplate.expire(cacheKey, NumberUtils.createLong(settingDto.getValue()), TimeUnit.SECONDS);
                } else {
                    throw new BusinessException(BusinessExceptionEnum.INVALID_PARAMS.getCode(), "你评论的太快了");
                }
            }
        }

        if (StringUtils.isNotBlank(ip)) {
            settingDto = settingFeignClient.info(appId, WEMEDIA_IP_COMMENT_ONE_HOUR_AMOUNT);
            if (settingDto != null && NumberUtils.isNumber(settingDto.getValue())) {
                String cacheKey = CACHE_NAME_IP_COMMENT_NUM + ip;
                long num = valueOperations.increment(cacheKey, 1);
                if (num == 1) {
                    redisTemplate.expire(cacheKey, 1, TimeUnit.HOURS);
                } else if (num > NumberUtils.createLong(settingDto.getValue())) {
                    throw new BusinessException(BusinessExceptionEnum.INVALID_PARAMS.getCode(), "你的IP评论太多了");
                }
            }
        }
    }
}
