package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.item.feign.dto.ItemInfoToAppDto;
import com.zqkh.item.feign.dto.ItemListToAppDto;
import com.zqkh.item.feign.dto.tag.ItemTagListToAppDto;

import java.util.List;

/**
 * App商品业务接口
 *
 * @author 东来
 * @create 2018/1/19 0019
 */
public interface AppItemService {

    /**
     * 获取APP商品列表
     * @param pageIndex:第几页
     * @param pageSize:每页显示多少条
     * @param catalogueId :商品类型编号
     * @param tag :商品标签
     * @return
     */
    PageResult<ItemListToAppDto> getItemListToApp(Integer pageIndex, Integer pageSize,String catalogueId,String tag);

    /**
     * 获取APP商品详情
     * @param id:商品编号
     * @return
     */
    ItemInfoToAppDto getItemInfoDtoToApp(String id);

    /**
     * 获取商品标签列表
     * @return
     */
    List<ItemTagListToAppDto> getItemTagList();
}
