package com.zqkh.webapi.context.service.impl;

import com.jovezhao.nest.starter.AppService;
import com.zqkh.common.result.PageResult;
import com.zqkh.file.feign.FileFeign;
import com.zqkh.item.feign.ItemClient;
import com.zqkh.item.feign.SupplierItemCategoryClient;
import com.zqkh.item.feign.dto.SupplierItemCategoryDto;
import com.zqkh.item.feign.dto.SupplierItemCategoryItemsDto;
import com.zqkh.item.feign.dto.SupplierItemInfoDto;
import com.zqkh.shop.feign.ShopApplicationClient;
import com.zqkh.shop.feign.dto.ShopDetailDto;
import com.zqkh.user.feign.UserClient;
import com.zqkh.webapi.context.service.SupplierMallService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author hty
 * @create 2018-05-14 15:35
 **/
@AppService
public class SupplierMallServiceImpl implements SupplierMallService {

    @Autowired
    UserClient userClient;

    @Autowired
    ItemClient itemClient;

    @Autowired
    FileFeign fileFeign;

    @Autowired
    SupplierItemCategoryClient supplierItemCategoryClient;

    @Autowired
    ShopApplicationClient shopApplicationClient;

    @Override
    public ShopDetailDto getSupplierMallInfo(String supplierId) {
        ShopDetailDto shopSettingDetail = shopApplicationClient.getShopSettingDetail(supplierId);
        Integer itemCount = itemClient.getItemCountBySupplierId(supplierId);
        shopSettingDetail.setItemCount(itemCount);
        return shopSettingDetail;
    }

    @Override
    public List<SupplierItemCategoryDto> getSupplierItemCategories(String supplierId) {
        List<SupplierItemCategoryDto> supplierItemCategories = supplierItemCategoryClient.getHasItemSupplierItemCategories(supplierId);
        return supplierItemCategories;
    }

    @Override
    public List<SupplierItemCategoryItemsDto> getSupplierIndexItems(String supplierId) {
        List<SupplierItemCategoryItemsDto> supplierIndexItems = supplierItemCategoryClient.getSupplierIndexItems(supplierId);
        supplierIndexItems.stream().forEach(item -> {
            item.getItems().stream().forEach(dto -> dto.setCover(fileFeign.getUrlByMd5(dto.getCover())));
        });
        return supplierIndexItems;
    }

    @Override
    public PageResult<SupplierItemInfoDto> getSupplierAllItems(String supplierId, Integer pageIndex, Integer pageSize) {
        PageResult<SupplierItemInfoDto> supplierAllItems = supplierItemCategoryClient.getSupplierAllItems(supplierId, pageIndex, pageSize);
        supplierAllItems.getList().stream().forEach(dto -> dto.setCover(fileFeign.getUrlByMd5(dto.getCover())));
        return supplierAllItems;
    }

    @Override
    public List<SupplierItemCategoryDto> getParentAndChildrenCategories(String parentCategoryId) {
        return supplierItemCategoryClient.getParentAndChildrenCategories(parentCategoryId);
    }

    @Override
    public PageResult<SupplierItemInfoDto> getSupplierItemsByCategoryId(String categoryId, Integer pageIndex, Integer pageSize) {
        PageResult<SupplierItemInfoDto> supplierAllItems = supplierItemCategoryClient.getSupplierItemsByCategoryId(categoryId, pageIndex, pageSize);
        supplierAllItems.getList().stream().forEach(dto -> dto.setCover(fileFeign.getUrlByMd5(dto.getCover())));
        return supplierAllItems;
    }
}
