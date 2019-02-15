package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.dto.wemedia.type.TypeDto;
import com.zqkh.webapi.context.dto.wemedia.type.TypeListDto;

/**
 * 分类 Web Api 接口服务
 *
 * @author 悭梵
 * @date Created in 2018-04-06 17:23
 */
public interface TypeService {

    /**
     * 分类详情
     * @param id 分类编号
     * @return
     */
    TypeDto getTypeInfo(String id);

    /**
     * 分页查询分类信息
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageResult<TypeListDto> typeListPage(int pageIndex, int pageSize);
}
