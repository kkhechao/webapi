package com.zqkh.webapi.context.service.impl;

import com.zqkh.webapi.context.configurtion.properties.CloudConfigProperties;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.dto.ad.AdRequestEnum;
import com.zqkh.webapi.context.dto.ad.AdvertisementDto;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.service.AdvertisementService;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 广告业务实现层
 *
 * @author 东来
 * @create 2018/1/19 0019
 */
@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Resource
    private CloudConfigProperties cloudConfigProperties;


    @Override
    public List<AdvertisementDto> getAdListByBannerCode(HttpServletRequest request, String code) {
        if (ObjectUtils.isEmpty(code)) {
            throw new BusinessException(BusinessExceptionEnum.AD_BANNER_CODE_IS_NULL.getCode(), "广告位编码为空", code);
        }
        //TODO 因广告推广系统未创建,则所有广告全部写死
        switch (code) {
            case "MALL_0001":
                return this.mallBanner(request);
            case "GENE_0001":
                return this.geneBanner();
            case "HUFU_0001":
                return this.huFuBanner();
            case "MEDIA_0001":
                return this.mediaBanner();
            default:
                throw new BusinessException(BusinessExceptionEnum.AD_BANNER_CODE_ERR.getCode(), "广告位编码错误", code);
        }

    }


    /**
     * 商城广告
     *
     * @return
     */
    private List<AdvertisementDto> mallBanner(HttpServletRequest request) {


        String osType = request.getHeader("osType");

        String version = request.getHeader("version");

        String versionCodeSrc = request.getHeader("version_code");

        List<AdvertisementDto> result = new ArrayList<>();



        //直播广告
        if (cloudConfigProperties.getAd().getLive()) {
            AdvertisementDto advertisementDto0 = new AdvertisementDto();
            advertisementDto0.setRequestType(AdRequestEnum.URL);
            advertisementDto0.setCharacter("环球夫人");
            advertisementDto0.setCoverUrl("https://file.ccuol.com/image/banner-fenghui-new.png");
            Map<String, Object> param0 = new HashMap<>();
            param0.put("url", "http://live.ccuol.com/");
            param0.put("httpMethod", HttpMethod.GET);
            advertisementDto0.setParam(param0);
            result.add(advertisementDto0);
        }

        //妙龄1号广告
        AdvertisementDto advertisementDto1=new AdvertisementDto();
        advertisementDto1.setRequestType(AdRequestEnum.ITEM_INFO);
        advertisementDto1.setCharacter("秒龄1号");
        advertisementDto1.setCoverUrl("https://file.ccuol.com/image/miaoling1hao.jpg");
        Map<String,Object> param1=new HashMap<>();
        param1.put("httpMethod",HttpMethod.GET);
        param1.put("id","ca805013903ab462f8528c6813089a8ec");
        advertisementDto1.setParam(param1);
        result.add(advertisementDto1);


        //云美介绍
        AdvertisementDto advertisementDto2=new AdvertisementDto();
        advertisementDto2.setRequestType(AdRequestEnum.H5_REL);
        advertisementDto2.setCharacter("云美介绍");
        advertisementDto2.setCoverUrl("https://file.ccuol.com/image/5-121204193R0-50.png");
        Map<String,Object> param2=new HashMap<>();
        param2.put("httpMethod",HttpMethod.GET);
        param2.put("rel","h5_banner_yunmei");
        advertisementDto2.setParam(param2);
        result.add(advertisementDto2);


        AdvertisementDto advertisementDto3=new AdvertisementDto();
        advertisementDto3.setRequestType(AdRequestEnum.H5_REL);
        advertisementDto3.setCharacter("招商简章");
        advertisementDto3.setCoverUrl("https://file-pro.oss-cn-hangzhou.aliyuncs.com/zs-zhaoshang.jpg");
        Map<String, Object> param3 = new HashMap<>();
        param3.put("rel", "h5_investment_introduction");
        param3.put("httpMethod", HttpMethod.GET);
        advertisementDto3.setParam(param3);



        /*AdvertisementDto advertisementDto4=new AdvertisementDto();
        advertisementDto4.setRequestType(AdRequestEnum.H5_REL);
        advertisementDto4.setCharacter("公司简介");
        advertisementDto4.setCoverUrl("https://file-pro.oss-cn-hangzhou.aliyuncs.com/banner-jianjie.jpg");
        Map<String,Object> param4=new HashMap<>();
        param4.put("rel","h5_banner_company");
        param4.put("httpMethod",HttpMethod.GET);
        advertisementDto4.setParam(param4);*/


        AdvertisementDto advertisementDto5 = new AdvertisementDto();
        advertisementDto5.setRequestType(AdRequestEnum.H5_REL);
        advertisementDto5.setCharacter("中清商城简介");
        advertisementDto5.setCoverUrl("https://file.ccuol.com/%E9%A6%96%E9%A1%B5%E5%85%AC%E5%8F%B8banner.jpg");
        Map<String, Object> param5 = new HashMap<>();
        param5.put("rel", "h5_banner_mall");
        param5.put("httpMethod", HttpMethod.GET);
        Map<String, String> dataPram = new HashMap<>();
        dataPram.put("id", "11111");
        param5.put("data", dataPram);
        advertisementDto5.setParam(param5);


        if ("iOS".equals(osType)) {

            switch (version) {
                case "1.0.0":
                case "1.0.1":
                    advertisementDto3.setRequestType(AdRequestEnum.URL);
                    //advertisementDto4.setRequestType(AdRequestEnum.URL);
                    param3.put("url","http://h5.ccuol.com/app-h5/v1.0/app_investment_introduction.html");
                    //param4.put("url","http://h5.ccuol.com/app-h5/v1.0/app_company_intro.html");
                    break;
            }
        }

       /* if(!ObjectUtils.isEmpty(versionCodeSrc)){
            if("Android".equals(osType)){
                BigDecimal versionCode=new BigDecimal(versionCodeSrc);
                if(versionCode.doubleValue()<6){
                    advertisementDto3.setRequestType(AdRequestEnum.URL);
                    advertisementDto4.setRequestType(AdRequestEnum.URL);
                    param3.put("url","http://h5.ccuol.com/app-h5/v1.0/app_investment_introduction.html");
                    param4.put("url","http://h5.ccuol.com/app-h5/v1.0/app_company_intro.html");
                }
            }
        }*/

        result.add(advertisementDto3);
        result.add(advertisementDto5);

        return result;
    }


    /**
     * 商城广告
     *
     * @return
     */
    private List<AdvertisementDto> huFuBanner() {
        List<AdvertisementDto> result = new ArrayList<>();
        AdvertisementDto advertisementDto1 = new AdvertisementDto();
        advertisementDto1.setRequestType(AdRequestEnum.ITEM_INFO);
        advertisementDto1.setCharacter("锐变找到你最初的肌肤");
        advertisementDto1.setCoverUrl("http://zqkh-test.oss-cn-hangzhou.aliyuncs.com/lALPBbCc1WV-m6jNAcLNBBo_1050_450.png");
        result.add(advertisementDto1);
        return result;
    }


    /**
     * 基因广告
     *
     * @return
     */
    private List<AdvertisementDto> geneBanner() {
        List<AdvertisementDto> result = new ArrayList<>();
        AdvertisementDto advertisementDto1 = new AdvertisementDto();
        advertisementDto1.setRequestType(AdRequestEnum.URL);
        advertisementDto1.setCharacter("基因测试");
        advertisementDto1.setCoverUrl("http://zqkh-test.oss-cn-hangzhou.aliyuncs.com/lADPBbCc1WWguT3NAabNAu4_750_422.jpg");
        Map<String, Object> param = new HashMap<>();
        param.put("url", "http://zqkh-test.oss-cn-hangzhou.aliyuncs.com/%E4%B8%AD%E6%B8%85%E7%A7%91%E5%8D%8E%E5%AE%A3%E4%BC%A0%E7%89%8720140528_1.mp4");
        param.put("httpMethod", HttpMethod.GET);
        advertisementDto1.setParam(param);
        result.add(advertisementDto1);
        return result;
    }

    /**
     * 看键首页广告
     *
     * @return
     */
    private List<AdvertisementDto> mediaBanner() {
        List<AdvertisementDto> result = new ArrayList<AdvertisementDto>();

        AdvertisementDto advertisementDto = new AdvertisementDto();
        advertisementDto.setRequestType(AdRequestEnum.MEDIA);
        advertisementDto.setCharacter("中清健康专栏");
        advertisementDto.setCoverUrl("https://file.ccuol.com/image/kj_banner.png");

        Map<String, Object> param = new HashMap<>();
        param.put("title", "中清健康专栏");
        param.put("subtitle", "让专家来守护您的健康");
        advertisementDto.setParam(param);

        result.add(advertisementDto);

        return result;
    }

}
