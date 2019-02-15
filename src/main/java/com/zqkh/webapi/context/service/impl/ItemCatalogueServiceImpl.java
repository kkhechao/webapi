package com.zqkh.webapi.context.service.impl;

import com.zqkh.item.feign.ItemCatalogueClient;
import com.zqkh.item.feign.dto.ItemCatalogueRootTreeDto;
import com.zqkh.item.feign.dto.ItemCatalogueTreeDto;
import com.zqkh.webapi.context.dto.item.catalogue.RecommendCatalogueDto;
import com.zqkh.webapi.context.service.ItemCatalogueService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Set;
import java.util.TreeSet;

/**
 * 商品类型业务实现层
 *
 * @author 东来
 * @create 2018/3/17 0017
 */
@Service
public class ItemCatalogueServiceImpl implements ItemCatalogueService {


    @Resource
    private ItemCatalogueClient itemCatalogueClient;
    @Override
    public Set<RecommendCatalogueDto> getRecommendCatalogue() {
        ItemCatalogueRootTreeDto itemCatalogueRootTreeDto= itemCatalogueClient.getItemCatalogueTree();

        Set<RecommendCatalogueDto> recommendCatalogue=new TreeSet<>();
        if(!ObjectUtils.isEmpty(itemCatalogueRootTreeDto)){
            itemCatalogueRootTreeDto.getNote().forEach(n->{
               this.addRecommendCatalogueDto(recommendCatalogue,n);
            });
        }
        return recommendCatalogue;
    }

    private void addRecommendCatalogueDto(Set<RecommendCatalogueDto> recommendCatalogue, ItemCatalogueTreeDto itemCatalogueTreeDto){
            if(ObjectUtils.isEmpty(itemCatalogueTreeDto.getChildren())){
                RecommendCatalogueDto recommendCatalogueDto=new RecommendCatalogueDto();
                recommendCatalogueDto.setId(itemCatalogueTreeDto.getValue());
                recommendCatalogueDto.setName(itemCatalogueTreeDto.getLabel());
                if(ObjectUtils.isEmpty(itemCatalogueTreeDto.getIconUrl())){
                    recommendCatalogueDto.setIcon(this.getCatalogueIcon(itemCatalogueTreeDto.getValue()));
                }else{
                    recommendCatalogueDto.setIcon(itemCatalogueTreeDto.getIconUrl());
                }
                recommendCatalogue.add(recommendCatalogueDto);
            }else{
                itemCatalogueTreeDto.getChildren().forEach(n->{
                    this.addRecommendCatalogueDto(recommendCatalogue,n);
                });
            }
    }

    /**
     * 获取商品类型图标
     * @param id
     * @return
     */
    private String getCatalogueIcon(String id){
        switch (id){
            case "1":
                return "https://file-pro.oss-cn-hangzhou.aliyuncs.com/icon/ic_dajiankang@3x.png";
            case "2":
                return "https://file-pro.oss-cn-hangzhou.aliyuncs.com/icon/ic_yimei@3x.png";
            case "3":
                return "https://file-pro.oss-cn-hangzhou.aliyuncs.com/icon/ic_jiankang@3x.png";
            case "4":
                return "https://file-pro.oss-cn-hangzhou.aliyuncs.com/icon/ic_fuhu@3x.png";
            case "5":
                return "https://file-pro.oss-cn-hangzhou.aliyuncs.com/icon/ic_huazhuang@3x.png";
            case "6":
                return "https://file-pro.oss-cn-hangzhou.aliyuncs.com/icon/ic_mianshuang@3x.png";
            case "7":
                return "https://file-pro.oss-cn-hangzhou.aliyuncs.com/icon/ic_jinghua@3x.png";
            case "8":
                return "https://file-pro.oss-cn-hangzhou.aliyuncs.com/icon/ic_mianmo@3x.png";
            case "9":
                return "https://file-pro.oss-cn-hangzhou.aliyuncs.com/icon/ic_taozhuang@3x.png";
            case "10":
                return "https://file-pro.oss-cn-hangzhou.aliyuncs.com/icon/ic_jiancec@3x.png";
            case "11":
                return "https://file-pro.oss-cn-hangzhou.aliyuncs.com/icon/ic_yimei@3x.png";
            case "12":
                return "https://file-pro.oss-cn-hangzhou.aliyuncs.com/icon/ic_zuiba@3x.png";
            case "13":
                return "https://file-pro.oss-cn-hangzhou.aliyuncs.com/icon/ic_pifuke@3x.png";
            case "14":
                return "https://file-pro.oss-cn-hangzhou.aliyuncs.com/icon/ic_yanjing@3x.png";
            default:
                return "https://file-pro.oss-cn-hangzhou.aliyuncs.com/icon/ic_jiankang@3x.png";
        }
    }
}
