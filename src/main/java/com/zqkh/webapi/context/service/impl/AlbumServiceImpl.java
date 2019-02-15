package com.zqkh.webapi.context.service.impl;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.dto.wemedia.album.AlbumInfoDto;
import com.zqkh.webapi.context.dto.wemedia.album.AlbumListDto;
import com.zqkh.webapi.context.dto.wemedia.article.MediaTypeEnumDto;
import com.zqkh.webapi.context.dto.wemedia.browse.BrowseTypeDto;
import com.zqkh.webapi.context.dto.wemedia.column.ColumnSimpleDto;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.AlbumService;
import com.zqkh.wemedia.feign.AlbumFeignClient;
import com.zqkh.wemedia.feign.BrowseFeignClient;
import com.zqkh.wemedia.feign.SubscribeFeignClient;
import com.zqkh.wemedia.feign.dto.AlbumDto;
import com.zqkh.wemedia.feign.vo.BrowseVo;
import com.zqkh.wemedia.feign.vo.SubscribeVo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 专辑业务实现层
 *
 * @author 东来
 * @create 2018/4/6 0006
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Resource
    private AlbumFeignClient albumFeignClient;

    @Resource
    private SubscribeFeignClient subscribeFeignClient;

    @Resource
    private BrowseFeignClient browseFeignClient;

    @Override
    public PageResult<AlbumListDto> getAlbumList(String typeId, Integer pageIndex, Integer pageSize) {

        PageResult<com.zqkh.wemedia.feign.dto.AlbumListDto> albumListDtoPageResult = albumFeignClient.listAlbum(pageIndex, pageSize, typeId);
        if (ObjectUtils.isEmpty(albumListDtoPageResult) || ObjectUtils.isEmpty(albumListDtoPageResult.getList())) {
            return new PageResult<>(albumListDtoPageResult.getTotalCount(), albumListDtoPageResult.getPageSize(), new ArrayList<>());
        }

        List<AlbumListDto> result = feignAlbumListDtoToApiDto(albumListDtoPageResult.getList());

        return new PageResult<>(albumListDtoPageResult.getTotalCount(), albumListDtoPageResult.getPageSize(), result);
    }

    @Override
    public AlbumInfoDto getAlbumInfo(String id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "专辑编号为空");
        }

        AlbumDto albumDto = albumFeignClient.getAlbum(id);
        if (ObjectUtils.isEmpty(albumDto)) {
            return null;
        }

        AlbumInfoDto albumInfoDto = new AlbumInfoDto();
        albumInfoDto.setCount(albumDto.getArticleCount());
        albumInfoDto.setId(albumDto.getId());
        albumInfoDto.setName(albumDto.getName());
        albumInfoDto.setKeyword(albumDto.getKeyword());
        albumInfoDto.setCreateUserId(albumDto.getCreateUserId());
        albumInfoDto.setCreateTime(albumDto.getCreateTime());
        albumInfoDto.setSynopsis(albumDto.getSynopsis());
        albumInfoDto.setMediaType(MediaTypeEnumDto.getMediaTypeEnumVo(albumDto.getMediaType()));
        albumInfoDto.setIconUrl(albumDto.getIconUrl());

        com.zqkh.wemedia.feign.dto.column.ColumnSimpleDto feignColumnSimpleDto = albumDto.getColumn();
        if (feignColumnSimpleDto != null) {
            ColumnSimpleDto columnSimpleDto = new ColumnSimpleDto();
            columnSimpleDto.setId(feignColumnSimpleDto.getId());
            columnSimpleDto.setName(feignColumnSimpleDto.getName());
            albumInfoDto.setColumn(columnSimpleDto);
        }

        /* 获取并设置订阅状态 */
        JWTUserDto userDto = AuthManager.currentUser();
        if (!ObjectUtils.isEmpty(userDto)) {
            /* 添加浏览历史 */
            BrowseVo browseVo = new BrowseVo();
            browseVo.setUserId(userDto.getUserId());
            browseVo.setObjectId(albumDto.getId());
            browseVo.setBrowseType(BrowseTypeDto.ALBUM.name());
            browseFeignClient.addBrowse(browseVo);

            SubscribeVo subscribeVo = new SubscribeVo();
            subscribeVo.setUserId(userDto.getUserId());
            subscribeVo.setAlbumId(id);
            albumInfoDto.setSubscribe(subscribeFeignClient.vaildSubscribe(subscribeVo));
            albumInfoDto.setSubscribeNum(subscribeFeignClient.subscribeAlbumNum(id));
        }

        return albumInfoDto;
    }

    @Override
    public List<AlbumListDto> choiceAlbumList(String lastAlbumIds) {
        List<com.zqkh.wemedia.feign.dto.AlbumListDto> dataList = albumFeignClient.choiceAlbumList(lastAlbumIds);
        if (dataList == null || dataList.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        return feignAlbumListDtoToApiDto(dataList);
    }

    private List<AlbumListDto> feignAlbumListDtoToApiDto(List<com.zqkh.wemedia.feign.dto.AlbumListDto> feignAlbumListDtoList) {
        return feignAlbumListDtoList.stream().map(n -> {
            AlbumListDto albumListDto = new AlbumListDto();
            albumListDto.setId(n.getId());
            albumListDto.setIconUrl(n.getIconUrl());
            albumListDto.setName(n.getName());
            albumListDto.setMediaType(n.getMediaType());
            albumListDto.setSynopsis(n.getSynopsis());
            albumListDto.setArticleCount(n.getArticleCount());
            albumListDto.setReadCount(n.getReadCount());
            albumListDto.setCreateUserId(n.getCreateUserId());
            albumListDto.setCreateTime(n.getCreateTime());
            return albumListDto;
        }).collect(Collectors.toList());
    }
}
