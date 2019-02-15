package com.zqkh.webapi.context.service;

import com.zqkh.webapi.context.dto.ad.AdvertisementDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 广告业务接口
 *
 * @author 东来
 * @create 2018/1/19 0019
 */
public interface AdvertisementService {


    /**
     * 根据banner Code获取广告位广告列表
     * @param code:广告位编码
     * @return
     */
    List<AdvertisementDto> getAdListByBannerCode(HttpServletRequest request,String code);


}
