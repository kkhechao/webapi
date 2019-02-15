package com.zqkh.webapi.context.service.impl;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.dto.wemedia.browse.BrowseDto;
import com.zqkh.webapi.context.dto.wemedia.browse.BrowseTypeDto;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.BrowseService;
import com.zqkh.wemedia.feign.BrowseFeignClient;
import com.zqkh.wemedia.feign.vo.BrowseVo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * 浏览历史WebAPI业务实现类
 *
 * @author 悭梵
 * @date Created in 2018-05-08 15:53
 */
@Service
public class BrowseServiceImpl implements BrowseService {

    @Resource
    private BrowseFeignClient browseFeignClient;

    @Override
    public void addBrowse(String browseType, String objectId) {
        if (ObjectUtils.isEmpty(objectId)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "浏览类型为空");
        } else {
            if (!(BrowseTypeDto.ARTICLE.name().equals(browseType) || BrowseTypeDto.ALBUM.name().equals(browseType) || BrowseTypeDto.TYPE.name().equals(browseType))) {
                throw new BusinessException(BusinessExceptionEnum.INVALID_PARAMS.getCode(), "浏览类型错误");
            }
        }
        if (ObjectUtils.isEmpty(objectId)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "浏览对象标识为空");
        }
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BusinessException(BusinessExceptionEnum.LOGIN_FAIL.getCode(), "请登录");
        }
        BrowseVo browseVo = new BrowseVo();
        browseVo.setBrowseType(browseType);
        browseVo.setUserId(userDto.getUserId());
        browseVo.setObjectId(objectId);

        browseFeignClient.addBrowse(browseVo);
    }

    @Override
    public BrowseDto latelyBrowseByUserId() {
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BusinessException(BusinessExceptionEnum.LOGIN_FAIL.getCode(), "请登录");
        }
        return feignBrowseDtoToApiDto(browseFeignClient.latelyBrowseByUserId(userDto.getUserId()));
    }

    @Override
    public PageResult<BrowseDto> list(Integer pageIndex, Integer pageSize) {
        JWTUserDto userDto = AuthManager.currentUser();
        if (ObjectUtils.isEmpty(userDto)) {
            return new PageResult(0, pageSize, Collections.EMPTY_LIST);
        }
        PageResult<com.zqkh.wemedia.feign.dto.browse.BrowseDto> feignPageResult = browseFeignClient.list(pageIndex, pageSize, userDto.getUserId());
        if (ObjectUtils.isEmpty(feignPageResult) || ObjectUtils.isEmpty(feignPageResult.getList())) {
            return new PageResult(feignPageResult.getTotalCount(), feignPageResult.getPageSize(), Collections.EMPTY_LIST);
        }
        return new PageResult(feignPageResult.getTotalCount(), feignPageResult.getPageSize(), feignPageResult.getList().stream().map(item -> feignBrowseDtoToApiDto(item)).filter(o -> !ObjectUtils.isEmpty(o)).collect(Collectors.toList()));
    }

    /**
     * feign接口返回对象装换
     *
     * @param feignBrowseDto
     * @return
     */
    private BrowseDto feignBrowseDtoToApiDto(com.zqkh.wemedia.feign.dto.browse.BrowseDto feignBrowseDto) {
        if (ObjectUtils.isEmpty(feignBrowseDto)) {
            return null;
        }
        BrowseDto resultBrowseDto = new BrowseDto();
        resultBrowseDto.setId(feignBrowseDto.getId());
        resultBrowseDto.setNum(feignBrowseDto.getNum());
        resultBrowseDto.setUserId(feignBrowseDto.getUserId());
        resultBrowseDto.setObjectId(feignBrowseDto.getObjectId());
        resultBrowseDto.setCreateTime(feignBrowseDto.getCreateTime());
        resultBrowseDto.setModifyTime(feignBrowseDto.getModifyTime());
        resultBrowseDto.setBrowseTypeDto(feignBrowseDto.getBrowseTypeDto() == null ? null : BrowseTypeDto.valueOf(feignBrowseDto.getBrowseTypeDto().name()));
        return resultBrowseDto;
    }
}
