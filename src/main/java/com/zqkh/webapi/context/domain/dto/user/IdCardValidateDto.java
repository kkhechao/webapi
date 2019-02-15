package com.zqkh.webapi.context.domain.dto.user;

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
public class IdCardValidateDto implements Serializable {

    @ApiModelProperty(value = "是否通过验证", name = "validate", dataType = "boolean", required = true)
    private Boolean validate = false;
    @ApiModelProperty(value = "信息", name = "msg", dataType = "String", required = true)
    private String msg;
}
