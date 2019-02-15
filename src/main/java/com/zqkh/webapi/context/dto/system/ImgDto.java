package com.zqkh.webapi.context.dto.system;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ImgDto {
    @NotNull(message = "文件类型不能为空")
    private String contentType;

    @NotNull(message = "图片不能为空")
    private String imgBase64;
}
