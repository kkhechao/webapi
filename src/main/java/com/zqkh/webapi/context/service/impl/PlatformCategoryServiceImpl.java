package com.zqkh.webapi.context.service.impl;

import com.jovezhao.nest.starter.AppService;
import com.zqkh.item.feign.PlatformCategoryClient;
import com.zqkh.item.feign.dto.platfrom.category.PlatformCategoryDto;
import com.zqkh.item.feign.dto.platfrom.category.TreePlatformCategoryDto;
import com.zqkh.webapi.context.service.PlatformCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author hty
 * @create 2018-06-01 17:26
 **/
@AppService
public class PlatformCategoryServiceImpl implements PlatformCategoryService {

    @Autowired
    PlatformCategoryClient platformCategoryClient;

    @Override
    public List<PlatformCategoryDto> getPlatformCategories(String parentId) {
        return platformCategoryClient.getPlatformCategories(parentId);
    }

    @Override
    public List<TreePlatformCategoryDto> getTreePlatformCategories() {
        return platformCategoryClient.getTreePlatformCategories();
    }
}
