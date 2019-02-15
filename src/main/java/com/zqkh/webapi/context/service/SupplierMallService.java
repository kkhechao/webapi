package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.item.feign.dto.SupplierItemCategoryDto;
import com.zqkh.item.feign.dto.SupplierItemCategoryItemsDto;
import com.zqkh.item.feign.dto.SupplierItemInfoDto;
import com.zqkh.shop.feign.dto.ShopDetailDto;

import java.util.List;

public interface SupplierMallService {
    ShopDetailDto getSupplierMallInfo(String supplierId);

    List<SupplierItemCategoryDto> getSupplierItemCategories(String supplierId);

    List<SupplierItemCategoryItemsDto> getSupplierIndexItems(String supplierId);

    PageResult<SupplierItemInfoDto> getSupplierAllItems(String supplierId, Integer pageIndex, Integer pageSize);

    List<SupplierItemCategoryDto> getParentAndChildrenCategories(String parentCategoryId);

    PageResult<SupplierItemInfoDto> getSupplierItemsByCategoryId(String categoryId, Integer pageIndex, Integer pageSize);
}
