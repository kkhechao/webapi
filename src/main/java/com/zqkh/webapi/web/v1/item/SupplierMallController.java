package com.zqkh.webapi.web.v1.item;

import com.zqkh.common.result.PageResult;
import com.zqkh.item.feign.dto.SupplierItemCategoryDto;
import com.zqkh.item.feign.dto.SupplierItemCategoryItemsDto;
import com.zqkh.item.feign.dto.SupplierItemInfoDto;
import com.zqkh.shop.feign.dto.ShopDetailDto;
import com.zqkh.webapi.context.service.SupplierMallService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hty
 * @create 2018-05-14 14:44
 **/
@RestController
@RequestMapping("/supplier")
@Slf4j
public class SupplierMallController {

    @Autowired
    SupplierMallService supplierMallService;

    @GetMapping("/mall/getSupplierMallInfo")
    @ApiOperation(value = "api_supplier_mall_info", notes = "店铺基本信息")
    ShopDetailDto getSupplierMallInfo(@RequestParam(required = false,name = "supplierId") String supplierId) {
        return supplierMallService.getSupplierMallInfo(supplierId);
    }

    @GetMapping("/mall/getSupplierItemCategories")
    @ApiOperation(value = "api_get_supplier_item_categories", notes = "获取店铺分类")
    List<SupplierItemCategoryDto> getSupplierItemCategories(@RequestParam(name = "supplierId") String supplierId){
        return supplierMallService.getSupplierItemCategories(supplierId);
    }

    @ApiOperation(value = "api_get_supplier_index_items", notes = "获取店铺首页商品列表")
    @GetMapping("/mall/getSupplierIndexItems")
    List<SupplierItemCategoryItemsDto> getSupplierIndexItems(@RequestParam(required = false, name = "supplierId") String supplierId){
        return supplierMallService.getSupplierIndexItems(supplierId);
    }

    @ApiOperation(value = "api_get_supplier_all_items", notes = "获取店铺所有商品列表")
    @GetMapping("/mall/getSupplierAllItems")
    PageResult<SupplierItemInfoDto> getSupplierAllItems(@RequestParam(name = "supplierId") String supplierId,
                                                        @RequestParam(value = "pageIndex") Integer pageIndex,
                                                        @RequestParam(value = "pageSize") Integer pageSize){
        return supplierMallService.getSupplierAllItems(supplierId,pageIndex,pageSize);
    }

    @ApiOperation(value = "api_get_parent_and_children_categories", notes = "获取平铺分类列表")
    @GetMapping("/mall/getParentAndChildrenCategories")
    List<SupplierItemCategoryDto> getParentAndChildrenCategories(@RequestParam("parentCategoryId") String parentCategoryId){
        return supplierMallService.getParentAndChildrenCategories(parentCategoryId);
    }

    @ApiOperation(value = "api_get_supplier_items_by_category_id", notes = "根据分类ID获取分类商品列表")
    @GetMapping("/mall/getSupplierItemsByCategoryId")
    PageResult<SupplierItemInfoDto> getSupplierItemsByCategoryId(@RequestParam(name = "categoryId") String categoryId,
                                                                 @RequestParam(value = "pageIndex") Integer pageIndex,
                                                                 @RequestParam(value = "pageSize") Integer pageSize){
        return supplierMallService.getSupplierItemsByCategoryId(categoryId,pageIndex,pageSize);
    }
}
