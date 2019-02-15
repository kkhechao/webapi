package com.zqkh.webapi.context.service.impl;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.dto.wemedia.column.ColumnDto;
import com.zqkh.webapi.context.dto.wemedia.column.ColumnListDto;
import com.zqkh.webapi.context.dto.wemedia.type.TypeSimpleDto;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.ColumnService;
import com.zqkh.webapi.context.service.FollowService;
import com.zqkh.wemedia.feign.ColumnFeignClient;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 栏目管理业务实现
 *
 * @author 悭梵
 * @date Created in 2018-06-01 16:34
 */
@Service
public class ColumnServiceImpl implements ColumnService {

    @Resource
    private ColumnFeignClient columnFeignClient;

    @Resource
    private FollowService followService;


    @Override
    public ColumnDto info(String id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "专栏标识为空");
        }

        com.zqkh.wemedia.feign.dto.column.ColumnDto feignColumnDto = columnFeignClient.info(id);
        ColumnDto columnDto = new ColumnDto();
        columnDto.setId(feignColumnDto.getId());
        columnDto.setName(feignColumnDto.getName());
        columnDto.setIntroduce(feignColumnDto.getIntroduce());
        columnDto.setIconResourceId(feignColumnDto.getIconResourceId());
        columnDto.setIconResourceUrl(feignColumnDto.getIconResourceUrl());
        columnDto.setCreateTime(feignColumnDto.getCreateTime());
        columnDto.setFollowNum(followService.followCount(id));
        columnDto.setStatus(feignColumnDto.getStatus());
        columnDto.setStatusTime(feignColumnDto.getStatusTime());
        columnDto.setSubhead(feignColumnDto.getSubhead());

        JWTUserDto userDto = AuthManager.currentUser();
        if (!ObjectUtils.isEmpty(userDto)) {
            columnDto.setFollow(followService.isFollow(id));
        }
        columnDto.setFileList(feignColumnDto.getFileList());

        columnDto.setTypeList(Optional.ofNullable(feignColumnDto.getTypeList())
                .orElse(Collections.emptyList())
                .stream()
                .filter(o -> o != null)
                .map(type -> {
                    return new TypeSimpleDto(type.getId(), type.getName(), type.getEname());
                })
                .collect(Collectors.toList())
        );

        columnDto.setExpert(feignColumnDto.getExpertDto());

        return columnDto;
    }

    @Override
    public PageResult<ColumnListDto> list(Integer pageIndex, Integer pageSize) {
        boolean isloginUser = AuthManager.currentUser() != null;
        PageResult<com.zqkh.wemedia.feign.dto.column.ColumnListDto> feignPageResult = columnFeignClient.list(null, pageIndex, pageSize);
        return new PageResult<ColumnListDto>(feignPageResult.getTotalCount(), feignPageResult.getPageSize(), Optional.ofNullable(feignPageResult.getList()).orElse(Collections.emptyList()).stream().map(feignColumnListDto -> {
            ColumnListDto columnListDto = new ColumnListDto();

            columnListDto.setId(feignColumnListDto.getId());
            columnListDto.setName(feignColumnListDto.getName());
            columnListDto.setStatus(feignColumnListDto.getStatus());
            columnListDto.setSubhead(feignColumnListDto.getSubhead());
            columnListDto.setStatusTime(feignColumnListDto.getStatusTime());
            columnListDto.setIntroduce(feignColumnListDto.getIntroduce());
            columnListDto.setIconResourceUrl(feignColumnListDto.getIconResourceUrl());
            columnListDto.setCreateTime(feignColumnListDto.getCreateTime());
            columnListDto.setFollowNum(followService.followCount(feignColumnListDto.getId()));
            if (isloginUser) {
                columnListDto.setFollow(followService.isFollow(feignColumnListDto.getId()));
            }
            columnListDto.setTypeList(Optional.ofNullable(feignColumnListDto.getTypeList())
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(o -> o != null)
                    .map(type -> {
                        return new TypeSimpleDto(type.getId(), type.getName(), type.getEname());
                    })
                    .collect(Collectors.toList())
            );

            return columnListDto;
        }).collect(Collectors.toList()));
    }
}
