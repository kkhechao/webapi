package com.zqkh.webapi.context.dto.ad;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * 广告DTO
 * @author 东来
 * @create 2018/1/19 0019
 */
@Getter
@Setter
@NoArgsConstructor
public class AdvertisementDto {


    /**
     * 广告跳转类型
     */
    private AdRequestEnum requestType;

    /**
     * 广告封面图链接地址
     */
    private String coverUrl;

    /**
     * 广告传参
     */
    private Map<String,Object> param;

    /**
     * 广告文字介绍
     */
    private String  character;




}
