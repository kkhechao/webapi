package com.zqkh.webapi.web.v1.item;

import com.zqkh.item.feign.ItemCatalogueClient;
import com.zqkh.item.feign.dto.ItemCatalogueDto;
import com.zqkh.item.feign.dto.ItemCatalogueRootTreeDto;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.dto.item.catalogue.RecommendCatalogueDto;
import com.zqkh.webapi.context.service.ItemCatalogueService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 *  商品类型控制层
 * @author  东来
 * @create 2018/1/18 0018
 */
@RestController
@RequestMapping("/item/catalogue")
public class ItemCatalogueController {


    @Resource
    private ItemCatalogueClient itemCatalogueClient;

    @Resource
    private ItemCatalogueService itemCatalogueService;

    @Anonymous
    @GetMapping("/parentId")
    @ApiOperation(value = "api_itemCatalogue_parentId", notes = "根据父节点编号查询商品类型列表")
    @ApiImplicitParam(name = "parentId",defaultValue = "0",value="商品类型父节点编号",paramType="query",dataType="String")
    public List<ItemCatalogueDto> getItemCatalogueByParentId(@RequestParam(name = "parentId",required = false) String parentId){
        List<ItemCatalogueDto> result=itemCatalogueClient.getItemCatalogue(parentId);
        return result;
    }

    @Anonymous
    @GetMapping("/tree")
    @ApiOperation(value = "api_itemCatalogue_tree", notes = "获取商品类型树形结构")
    public ItemCatalogueRootTreeDto getItemCatalogueTree(){
        return itemCatalogueClient.getItemCatalogueTree();
    }

    /**
     * 获取推荐商品类型
     * @return
     */
    @Anonymous
    @GetMapping("/recommend")
    @ApiOperation(value = "api_itemCatalogue_recommend", notes = "获取推荐商品类型")
    Set<RecommendCatalogueDto> getRecommendCatalogue(){
        return itemCatalogueService.getRecommendCatalogue();
    }

}
