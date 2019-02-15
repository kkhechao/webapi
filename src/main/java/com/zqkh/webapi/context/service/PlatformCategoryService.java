package com.zqkh.webapi.context.service;

import com.zqkh.item.feign.dto.platfrom.category.PlatformCategoryDto;
import com.zqkh.item.feign.dto.platfrom.category.TreePlatformCategoryDto;

import java.util.List;

public interface PlatformCategoryService {
    List<PlatformCategoryDto> getPlatformCategories(String parentId);

    List<TreePlatformCategoryDto> getTreePlatformCategories();

}
