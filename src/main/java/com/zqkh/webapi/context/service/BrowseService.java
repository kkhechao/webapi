package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.dto.wemedia.browse.BrowseDto;

/**
 * 浏览历史WebAPI层业务接口
 *
 * @author 悭梵
 * @date Created in 2018-05-08 15:52
 */
public interface BrowseService {
    /**
     * 新增浏览历史
     *
     * @param browseType 浏览类型，ARTICLE：文章, ALBUM：专辑  TYPE：分类
     * @param objectId
     */
    void addBrowse(String browseType, String objectId);

    /**
     * 最近的一条浏览记录
     *
     * @return
     */
    BrowseDto latelyBrowseByUserId();

    /**
     * 查询指定用户浏览历史
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageResult<BrowseDto> list(Integer pageIndex, Integer pageSize);
}
