package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.wemedia.feign.dto.ListenHealthDto;

/**
 * 听健康
 *
 * @author 悭梵
 * @date Created in 2018-07-11 16:49
 */
public interface ListenHealthService {
    /**
     * 挺健康列表
     *
     * @param typeId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageResult<ListenHealthDto> list(String typeId, String albumId, int pageIndex, int pageSize);


    /**
     * 听一段音乐，阅读数加1
     *
     * @param id
     */
    void listenHealth(String id);
}
