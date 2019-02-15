package com.zqkh.webapi.context.service.impl;

import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.FollowService;
import com.zqkh.wemedia.feign.FollowFeignClient;
import com.zqkh.wemedia.feign.vo.FollowVo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * 关注WEB API业务层实现
 *
 * @author 悭梵
 * @date Created in 2018-05-30 15:46
 */
@Service
public class FollowServiceImpl implements FollowService {
    @Resource
    private FollowFeignClient followFeignClient;

    @Override
    public void follow(String columnId) {
        if (ObjectUtils.isEmpty(columnId)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "专栏标识为空");
        }
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BusinessException(BusinessExceptionEnum.LOGIN_FAIL.getCode(), "请登录");
        }

        FollowVo vo = new FollowVo();
        vo.setColumnId(columnId);
        vo.setUserId(userDto.getUserId());

        followFeignClient.follow(vo);
    }

    @Override
    public void unfollow(String columnId) {
        if (ObjectUtils.isEmpty(columnId)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "专栏标识为空");
        }
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BusinessException(BusinessExceptionEnum.LOGIN_FAIL.getCode(), "请登录");
        }

        FollowVo vo = new FollowVo();
        vo.setColumnId(columnId);
        vo.setUserId(userDto.getUserId());

        followFeignClient.unfollow(vo);
    }

    @Override
    public boolean isFollow(String columnId) {
        if (ObjectUtils.isEmpty(columnId)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "专栏标识为空");
        }
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BusinessException(BusinessExceptionEnum.LOGIN_FAIL.getCode(), "请登录");
        }

        FollowVo vo = new FollowVo();
        vo.setColumnId(columnId);
        vo.setUserId(userDto.getUserId());

        return followFeignClient.isFollow(vo);
    }

    @Override
    public long followCount(String columnId) {
        if (ObjectUtils.isEmpty(columnId)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "专栏标识为空");
        }
        return followFeignClient.followCount(columnId);
    }
}
