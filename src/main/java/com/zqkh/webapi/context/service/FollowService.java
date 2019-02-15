package com.zqkh.webapi.context.service;

/**
 * 关注 WEB 业务层接口定义
 *
 * @author 悭梵
 * @date Created in 2018-05-30 14:15
 */
public interface FollowService {

    /**
     * 关注
     *
     * @param columnId
     */
    void follow(String columnId);

    /**
     * 取消关注
     *
     * @param columnId
     */
    void unfollow(String columnId);

    /**
     * 是否已关注
     *
     * @param columnId
     * @return
     */
    boolean isFollow(String columnId);

    /**
     * 指定专栏关注数量
     *
     * @param columnId
     * @return
     */
    long followCount(String columnId);
}
