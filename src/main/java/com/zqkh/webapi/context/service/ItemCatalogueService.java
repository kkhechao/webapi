package com.zqkh.webapi.context.service;

import com.zqkh.webapi.context.dto.item.catalogue.RecommendCatalogueDto;

import java.util.Set;

/**
 * 商品类型业务层
 *
 * @author 东来
 * @create 2018/3/17 0017
 */
public interface ItemCatalogueService {

    /**
     * 获取推荐商品类型
     * @return
     */
    Set<RecommendCatalogueDto> getRecommendCatalogue();
}
