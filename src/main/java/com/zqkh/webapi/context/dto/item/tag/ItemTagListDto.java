package com.zqkh.webapi.context.dto.item.tag;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 商品标签DTO
 *
 * @author 东来
 * @create 2018/3/17 0017
 */
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "商品标签模块")
public class ItemTagListDto implements Serializable {

    private String name;



}
