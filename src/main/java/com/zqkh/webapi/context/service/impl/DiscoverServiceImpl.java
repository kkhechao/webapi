package com.zqkh.webapi.context.service.impl;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.dto.wemedia.article.MediaTypeEnumDto;
import com.zqkh.webapi.context.dto.wemedia.discover.HealthChoicenessDto;
import com.zqkh.webapi.context.service.DiscoverService;
import com.zqkh.webapi.context.utils.ArticleUtil;
import com.zqkh.wemedia.feign.DiscoverFeignClient;
import com.zqkh.wemedia.feign.vo.MediaTypeEnum;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 发现业务实现层
 *
 * @author 东来
 * @create 2018/4/6 0006
 */
@Service
public class DiscoverServiceImpl implements DiscoverService {

    @Resource
    private DiscoverFeignClient discoverFeignClient;

    /**
     * 截取长度
     */
    private final static int FILTER_LENGTH = 20;

    @Override
    public PageResult<HealthChoicenessDto> healthChoiceness(String typeId, String albumId, Integer pageIndex, Integer pageSize) {

        PageResult<com.zqkh.wemedia.feign.dto.discover.HealthChoicenessDto> healthChoicenessDtoPageResult = discoverFeignClient.healthChoiceness(typeId, albumId, pageIndex, pageSize);
        if (ObjectUtils.isEmpty(healthChoicenessDtoPageResult.getList())) {
            return new PageResult<>(healthChoicenessDtoPageResult.getTotalCount(), healthChoicenessDtoPageResult.getPageSize(), new ArrayList<>());
        }

        List<HealthChoicenessDto> result = healthChoicenessDtoPageResult.
                getList().stream().map(n -> {
            HealthChoicenessDto healthChoicenessDto = new HealthChoicenessDto();
            healthChoicenessDto.setId(n.getId());
            healthChoicenessDto.setTitle(n.getTitle());
            healthChoicenessDto.setIconUrl(n.getIconUrl());
            healthChoicenessDto.setMediaUrl(n.getMediaUrl());
            healthChoicenessDto.setTypeName(n.getTypeList() == null || n.getTypeList().isEmpty() ? null : n.getTypeList().get(0).getName());
            healthChoicenessDto.setViewCount(n.getViewCount());
            healthChoicenessDto.setAuthorNickName(n.getAuthorNickName());
            healthChoicenessDto.setMediaType(MediaTypeEnumDto.getMediaTypeEnumVo(n.getMediaType().name()));
            healthChoicenessDto.setCreateTime(n.getCreateTime());
            if (n.getMediaType().equals(MediaTypeEnum.TEXT)) {
                if (!ObjectUtils.isEmpty(n.getContent())) {
                    String src = ArticleUtil.contentIntercept(n.getContent(), 100);
                    healthChoicenessDto.setSynopsis(src);
                }
            } else {
                healthChoicenessDto.setSynopsis(n.getSynopsis());
            }
            return healthChoicenessDto;
        }).collect(Collectors.toList());


        return new PageResult<>(healthChoicenessDtoPageResult.getTotalCount(), healthChoicenessDtoPageResult.getPageSize(), result);
    }
}
