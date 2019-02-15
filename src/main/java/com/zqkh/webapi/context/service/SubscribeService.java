package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.dto.wemedia.subscribe.SubscribeDto;

/**
 * 订阅WebAPI业务层接口定义
 *
 * @author 悭梵
 * @date Created in 2018-05-08 16:36
 */
public interface SubscribeService {
    /**
     * 订阅专辑
     *
     * @param albumId
     */
    void subscribe(String albumId);

    /**
     * 取消订阅专辑
     *
     * @param albumId
     */
    void unsubscribe(String albumId);

    /**
     * 验证是否已订阅专辑
     *
     * @param albumId 专题标识
     */
    boolean vaildSubscribe(String albumId);

    /**
     * 查询指定用户订阅列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageResult<SubscribeDto> list(Integer pageIndex, Integer pageSize);
}
