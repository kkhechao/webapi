package com.zqkh.webapi.context.service.impl;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.service.ListenHealthService;
import com.zqkh.wemedia.feign.ListenHealthFeignClient;
import com.zqkh.wemedia.feign.dto.ListenHealthDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 听健康实现
 *
 * @author 悭梵
 * @date Created in 2018-07-11 16:49
 */
@Service
public class ListenHealthServiceImpl implements ListenHealthService {

    @Resource
    private ListenHealthFeignClient listenHealthFeignClient;

    @Override
    public PageResult<ListenHealthDto> list(String typeId, String albumId, int pageIndex, int pageSize) {
        return listenHealthFeignClient.list(typeId, albumId, pageIndex, pageSize);
    }

    @Override
    public void listenHealth(String id) {
        listenHealthFeignClient.listenHealth(id);
    }
}
