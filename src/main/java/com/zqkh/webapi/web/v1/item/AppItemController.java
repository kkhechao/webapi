package com.zqkh.webapi.web.v1.item;

import com.zqkh.common.result.PageResult;
import com.zqkh.item.feign.dto.ItemInfoToAppDto;
import com.zqkh.item.feign.dto.ItemListToAppDto;
import com.zqkh.item.feign.dto.tag.ItemTagListToAppDto;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.service.AppItemService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * APP商品控制层
 *
 * @author 东来
 * @create 2018/1/19 0019
 */
@RestController
@RequestMapping("/item/app/")
@Slf4j
public class AppItemController {


    @Resource
    private AppItemService itemService;


    /**
     * 获取APP商品列表
     * @param pageIndex:第几页
     * @param pageSize:每页显示多少条
     * @param catalogueId :商品类型编号
     * @return
     */
    @Anonymous
    @GetMapping("list")
    @ApiOperation(value = "api_item_app_list", notes = "查询APP商品列表")
    @ApiImplicitParams({ @ApiImplicitParam(name = "pageIndex",defaultValue = "1",value="第几页",paramType="query",dataType="Integer")
            ,@ApiImplicitParam(name = "pageSize",defaultValue = "20",value = "每页显示多少条",paramType = "query",dataType = "Integer")
            ,@ApiImplicitParam(name = "catalogueId",value = "商品类型编号",paramType = "query",dataType = "String")
            ,@ApiImplicitParam(name = "tag",value = "商品标签",paramType = "query",dataType = "String")})
    PageResult<ItemListToAppDto> getItemListToApp(@RequestParam(name = "pageIndex",required = false,defaultValue = "1") Integer pageIndex,
                                                  @RequestParam(name = "pageSize",required = false,defaultValue = "20") Integer pageSize,
                                                  @RequestParam(name ="catalogueId",required = false)String catalogueId,
                                                  @RequestParam(name = "tag",required = false)String tag
                                                ){
        return itemService.getItemListToApp(pageIndex, pageSize,catalogueId,tag);
    };

    /**
     * 获取APP商品详情
     * @param id:商品编号
     * @return
     */
    @Anonymous
    @GetMapping("info")
    @ApiOperation(value = "api_item_app_info", notes = "查询APP商品详情")
    @ApiImplicitParam(name = "id",value="商品编号",paramType="query",dataType="Integer")
    ItemInfoToAppDto getItemInfoToApp(@RequestParam(name = "id",required = false) String id){
        return itemService.getItemInfoDtoToApp(id);
    };


    /**
     * 获取商品标签列表
     * @return
     */
    @Anonymous
    @GetMapping("/tag")
    @ApiOperation(value = "api_item_tag_list_app", notes = "查询商品标签列表")
    List<ItemTagListToAppDto> getItemTagList(){
        return itemService.getItemTagList();
    }



}
