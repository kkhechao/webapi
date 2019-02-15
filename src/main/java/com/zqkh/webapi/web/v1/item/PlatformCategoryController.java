package com.zqkh.webapi.web.v1.item;

import com.zqkh.item.feign.dto.platfrom.category.PlatformCategoryDto;
import com.zqkh.item.feign.dto.platfrom.category.TreePlatformCategoryDto;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.service.PlatformCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hty
 * @create 2018-05-08 15:00
 **/
@RestController
@RequestMapping("/platform")
public class PlatformCategoryController {


    @Autowired
    private PlatformCategoryService platformCategoryService;

    @Anonymous
    @ApiOperation(value = "api_get_platform_categories", notes = "获取平台商品分类")
    @GetMapping("/getPlatformCategories")
    List<PlatformCategoryDto> getPlatformCategories(@RequestParam(value = "parentId", required = false) String parentId) {
        return platformCategoryService.getPlatformCategories(parentId);
    }

    @Anonymous
    @ApiOperation(value = "api_get_tree_platform_categories", notes = "树形结构获取平台分类")
    @GetMapping("/platform/getTreePlatformCategories")
    List<TreePlatformCategoryDto> getTreePlatformCategories() {
        return platformCategoryService.getTreePlatformCategories();
    }
}
