package com.zqkh.webapi.context.dto.member;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class MemberGoodSDto {

//    public enum Cate {
//        SKIN,
//        GENE
//    }

    /**
     * 会员商品编号
     */
    private String id;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品标题
     */
    private BigDecimal mallPrice;

    /**
     * 商品封面图链接地址
     */
    private String surfacePhoto;

    /**
     * 商品标签
     */
    private List<String> tags;

    /**
     * 分类
     */
    private String cate;
    /**
     * 普通商品id
     */
    private String goodsId;


}
