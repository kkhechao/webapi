package com.zqkh.webapi.context.dto.item.catalogue;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * 推荐的商品类型
 *
 * @author 东来
 * @create 2018/3/17 0017
 */
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "推荐商品类型模块")
public class RecommendCatalogueDto implements Serializable,Comparable {

    @ApiModelProperty(value = "类型编号",name = "id",required = true)
    private String id;
    @ApiModelProperty(value = "类型名称",name = "name",required = true)
    private String name;
    @ApiModelProperty(value = "类型图标",name = "icon",required = false)
    private String icon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecommendCatalogueDto that = (RecommendCatalogueDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }


    @Override
    public int compareTo(Object o) {
        RecommendCatalogueDto p=(RecommendCatalogueDto)o;

        if (this.getId().compareTo(p.getId()) > 0) {
            return 1;
        }
        if (this.getId().compareTo(p.getId()) < 0) {
            return -1;
        }
        return 0;
    }
}
