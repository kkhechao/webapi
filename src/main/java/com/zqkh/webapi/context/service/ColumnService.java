package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.dto.wemedia.column.ColumnDto;
import com.zqkh.webapi.context.dto.wemedia.column.ColumnListDto;

/**
 * 栏目管理业务层接口定义
 *
 * @author 悭梵
 * @date Created in 2018-06-01 16:31
 */
public interface ColumnService {

    /**
     * 专栏信息
     *
     * @param id
     * @return
     */
    ColumnDto info(String id);

    /**
     * 专栏列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageResult<ColumnListDto> list(Integer pageIndex, Integer pageSize);
}
