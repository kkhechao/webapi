package com.zqkh.webapi.context.dto.archive.disease;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 流行区域
 *
 * @author 东来
 * @create 2018/6/26 0026
 */
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@ApiModel("发病地域占比")
public class EpidemicAreaDto implements Serializable {

    /**
     * 地域名称
     */
    @ApiModelProperty(value = "地域名称", name = "name")
    private String name;

    /**
     * 占比
     */
    @ApiModelProperty(value = "占比", name = "ratio")
    private BigDecimal ratio;

}
