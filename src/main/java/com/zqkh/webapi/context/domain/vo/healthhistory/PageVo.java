package com.zqkh.webapi.context.domain.vo.healthhistory;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PageVo implements Serializable {

    //region ----------------------------  分页参数

    @ApiModelProperty(value = "第几页", name = "pageIndex", dataType = "Integer", required = false)
    private Integer pageIndex = 1;

    @ApiModelProperty(value = "每页显示多少条", name = "pageSize", dataType = "Integer", required = false)
    private Integer pageSize = 20;

    //endregion

}
