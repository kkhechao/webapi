package com.zqkh.webapi.context.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author hty
 * @create 2017-12-16 10:14
 **/

@Getter
@Setter
public class AvatarDto {

    @NotNull(message = "图片地址不能为空")
    private String avatar;

    @NotNull(message = "文件标识不能为空")
    private String fileId;

}
