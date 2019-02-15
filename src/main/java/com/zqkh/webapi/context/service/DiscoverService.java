package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.dto.wemedia.discover.HealthChoicenessDto;


/**
 * 发现业务接口
 *
 * @author 东来
 * @create 2018/4/6 0006
 */
public interface DiscoverService  {


    /**
     * 健康精选
     * @param typeId:分类编号
     * @param albumId:专辑编号
     * @param pageIndex
     * @param pageSize
     * @return
     */

    PageResult<HealthChoicenessDto> healthChoiceness(String typeId, String albumId, Integer pageIndex, Integer pageSize);
}
