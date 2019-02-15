package com.zqkh.webapi.context.dto.wemedia.article;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 作者DTO
 *
 * @author 东来
 * @create 2018/4/4 0004
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "作者")
public class AuthorDto implements Serializable {

    @ApiModelProperty(value = "昵称", name = "nickName", required = true,dataType = "String")
    private String nickName;
    @ApiModelProperty(value = "作者头像链接地址", name = "headUrl", required = true,dataType = "String")
    private String headUrl;


}
