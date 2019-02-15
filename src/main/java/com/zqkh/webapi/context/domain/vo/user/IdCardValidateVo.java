package com.zqkh.webapi.context.domain.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 身份证验证
 *
 * @author 东来
 * @create 2018/6/14 0014
 */
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "身份证验证")
@Builder
@Getter
public class IdCardValidateVo implements Serializable {

    @ApiModelProperty(value = "身份证", name = "idCard", dataType = "String", required = true)
    private String idCard;

    @ApiModelProperty(value = "姓名", name = "name", dataType = "String", required = true)
    private String name;
}
