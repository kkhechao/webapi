package com.zqkh.webapi.web.v1.ad;

import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.dto.ad.AdvertisementDto;
import com.zqkh.webapi.context.service.AdvertisementService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 广告控制层
 *
 * @author 东来
 * @create 2018/1/19 0019
 */
@RestController
@RequestMapping("/spread")
public class AdController {

    @Resource
    private AdvertisementService advertisementService;


    /**
     * 根据banner Code获取广告位广告列表
     * @param code:广告位编码
     * @return
     */
    @Anonymous
    @GetMapping("")
    @ApiOperation(value = "api_ad_list", notes = "广告列表")
    @ApiImplicitParam(name = "code",value = "广告位编码,GENE_0001(基因广告),MALL_0001(商城首页广告),HUFU_0001(护肤广告),MEDIA_0001(看键首页广告)",required = true,paramType="query",dataType="String")
    List<AdvertisementDto> getAdListByBannerCode(HttpServletRequest request,String code){
        return advertisementService.getAdListByBannerCode(request,code);
    };

}
