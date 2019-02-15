package com.zqkh.webapi.context.service.impl;

import com.zqkh.common.exception.BusinessException;
import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.dto.wemedia.article.MediaTypeEnumDto;
import com.zqkh.webapi.context.dto.wemedia.subscribe.SubscribeDto;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.SubscribeService;
import com.zqkh.wemedia.feign.SubscribeFeignClient;
import com.zqkh.wemedia.feign.dto.subscribe.SubscribeAlbumDto;
import com.zqkh.wemedia.feign.vo.SubscribeVo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * 订阅WebAPI业务层实现类
 *
 * @author 悭梵
 * @date Created in 2018-05-08 16:49
 */
@Service
public class SubscribeServiceImpl implements SubscribeService {

    @Resource
    private SubscribeFeignClient subscribeFeignClient;

    @Override
    public void subscribe(String albumId) {
        if (ObjectUtils.isEmpty(albumId)) {
            throw new BusinessException("订阅专辑标识为空", albumId);
        }
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BusinessException("请登录", userDto);
        }
        SubscribeVo subscribeVo = new SubscribeVo();
        subscribeVo.setUserId(userDto.getUserId());
        subscribeVo.setAlbumId(albumId);

        subscribeFeignClient.subscribe(subscribeVo);
    }

    @Override
    public void unsubscribe(String albumId) {
        if (ObjectUtils.isEmpty(albumId)) {
            throw new BusinessException("专辑标识为空", albumId);
        }
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BusinessException("请登录", userDto);
        }
        subscribeFeignClient.unsubscribe(userDto.getUserId(), albumId);
    }

    @Override
    public boolean vaildSubscribe(String albumId) {
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BusinessException("请登录", userDto);
        }
        SubscribeVo subscribeVo = new SubscribeVo();
        subscribeVo.setUserId(userDto.getUserId());
        subscribeVo.setAlbumId(albumId);
        return subscribeFeignClient.vaildSubscribe(subscribeVo);
    }

    @Override
    public PageResult<SubscribeDto> list(Integer pageIndex, Integer pageSize) {
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            return new PageResult(0, pageSize, Collections.EMPTY_LIST);
        }
        PageResult<com.zqkh.wemedia.feign.dto.subscribe.SubscribeDto> feignPageResult = subscribeFeignClient.list(pageIndex, pageSize, userDto.getUserId());
        if (ObjectUtils.isEmpty(feignPageResult) || ObjectUtils.isEmpty(feignPageResult.getList())) {
            return new PageResult(feignPageResult.getTotalCount(), feignPageResult.getPageSize(), Collections.EMPTY_LIST);
        }
        return new PageResult(feignPageResult.getTotalCount(), feignPageResult.getPageSize(), feignPageResult.getList().stream().map(item -> feignSubscribeDtoToApiDto(item)).filter(o -> !ObjectUtils.isEmpty(o)).collect(Collectors.toList()));
    }

    /**
     * feign接口返回对象装换
     *
     * @param feignSubscribeDto
     * @return
     */
    private SubscribeDto feignSubscribeDtoToApiDto(com.zqkh.wemedia.feign.dto.subscribe.SubscribeDto feignSubscribeDto) {
        if (ObjectUtils.isEmpty(feignSubscribeDto)) {
            return null;
        }
        SubscribeDto resultSubscribeDto = new SubscribeDto();
        resultSubscribeDto.setId(feignSubscribeDto.getId());
        resultSubscribeDto.setUserId(feignSubscribeDto.getUserId());
        resultSubscribeDto.setCreateTime(feignSubscribeDto.getCreateTime());

        SubscribeAlbumDto subscribeAlbumDto = feignSubscribeDto.getSubscribeAlbum();
        if (!ObjectUtils.isEmpty(subscribeAlbumDto)) {
            resultSubscribeDto.setAlbumId(subscribeAlbumDto.getId());
            resultSubscribeDto.setAlbumName(subscribeAlbumDto.getName());
            resultSubscribeDto.setAlbumSynopsis(subscribeAlbumDto.getSynopsis());
            resultSubscribeDto.setAlbumKeyword(subscribeAlbumDto.getKeyword());
            resultSubscribeDto.setAlbumIconResourceUrl(subscribeAlbumDto.getIconResourceUrl());
            resultSubscribeDto.setAlbumAuthor(subscribeAlbumDto.getAuthor());
            resultSubscribeDto.setAlbumCreateTime(subscribeAlbumDto.getCreateTime());
            resultSubscribeDto.setAlbumModifyTime(subscribeAlbumDto.getModifyTime());
            resultSubscribeDto.setAlbumArticleCount(subscribeAlbumDto.getArticleCount());
            resultSubscribeDto.setAlbumViewCount(subscribeAlbumDto.getViewCount());
            resultSubscribeDto.setLastBrowseAfterArticleUpdateCount(subscribeAlbumDto.getLastBrowseAfterArticleUpdateCount());
            if (subscribeAlbumDto.getMediaType() != null) {
                resultSubscribeDto.setAlbumMediaType(MediaTypeEnumDto.getMediaTypeEnumVo(subscribeAlbumDto.getMediaType().name()));
            }
        }
        return resultSubscribeDto;
    }
}
