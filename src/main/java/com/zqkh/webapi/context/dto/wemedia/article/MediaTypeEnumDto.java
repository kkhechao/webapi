package com.zqkh.webapi.context.dto.wemedia.article;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 多媒体在类型
 */
@ApiModel(description = "多媒体类型")
public enum MediaTypeEnumDto {


    /**
     * 视频
     */
    @ApiModelProperty(value = "视频", name = "VIDEO", dataType = "String")
    VIDEO,
    /**
     * 音频
     */
    @ApiModelProperty(value = "音频", name = "AUDIO", dataType = "String")
    AUDIO,
    /**
     * 文本
     */
    @ApiModelProperty(value = "文本", name = "TEXT", dataType = "String")
    TEXT
    ;

    public static MediaTypeEnumDto getMediaTypeEnumVo(String name){
        for (MediaTypeEnumDto mediaType: MediaTypeEnumDto.values()) {
            if(mediaType.name().equals(name)){
                return mediaType;
            }
        }
        return null;
    }



}
