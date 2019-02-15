package com.zqkh.webapi.context.service.impl;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.dto.wemedia.browse.BrowseTypeDto;
import com.zqkh.webapi.context.dto.wemedia.type.TypeDto;
import com.zqkh.webapi.context.dto.wemedia.type.TypeListDto;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.TypeService;
import com.zqkh.wemedia.feign.BrowseFeignClient;
import com.zqkh.wemedia.feign.TypeFeignClient;
import com.zqkh.wemedia.feign.vo.BrowseVo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * 分类 Web Api 接口服务实现
 *
 * @author 悭梵
 * @date Created in 2018-04-06 17:24
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Resource
    private TypeFeignClient typeFeignClient;

    @Resource
    private BrowseFeignClient browseFeignClient;

    @Override
    public TypeDto getTypeInfo(String id) {
        com.zqkh.wemedia.feign.dto.TypeDto feignTypeDto = typeFeignClient.getType(id);
        TypeDto typeDto = new TypeDto();
        typeDto.setId(feignTypeDto.getId());
        typeDto.setName(feignTypeDto.getName());
        typeDto.setEname(feignTypeDto.getEname());
        typeDto.setIconUrl(feignTypeDto.getIconUrl());
        typeDto.setArticleCount(feignTypeDto.getArticleCount());
        typeDto.setSynopsis(feignTypeDto.getSynopsis());

        JWTUserDto userDto = AuthManager.currentUser();
        if (userDto != null) {
            /* 添加浏览历史 */
            BrowseVo browseVo = new BrowseVo();
            browseVo.setUserId(userDto.getUserId());
            browseVo.setObjectId(typeDto.getId());
            browseVo.setBrowseType(BrowseTypeDto.TYPE.name());
            browseFeignClient.addBrowse(browseVo);
        }
        return typeDto;
    }

    @Override
    public PageResult<TypeListDto> typeListPage(int pageIndex, int pageSize) {
        PageResult<com.zqkh.wemedia.feign.dto.TypeListDto> pageResultTypeDto = typeFeignClient.searchType(pageIndex, pageSize, null);

        if (ObjectUtils.isEmpty(pageResultTypeDto) || ObjectUtils.isEmpty(pageResultTypeDto.getList())) {
            return new PageResult<>(pageResultTypeDto.getTotalCount(), pageResultTypeDto.getPageSize(), null);
        }

        return new PageResult<>(pageResultTypeDto.getTotalCount(), pageResultTypeDto.getPageSize(), pageResultTypeDto.getList().stream().map(item -> {
            TypeListDto typeListDto = new TypeListDto();
            typeListDto.setId(item.getId());
            typeListDto.setName(item.getName());
            typeListDto.setEname(item.getEname());
            typeListDto.setIconUrl(item.getIconUrl());
            typeListDto.setArticleCount(item.getArticleCount());
            typeListDto.setSynopsis(item.getSynopsis());
            return typeListDto;
        }).filter(o -> !ObjectUtils.isEmpty(o)).collect(Collectors.toList()));
    }
}
