package com.zqkh.webapi.context.dto.wemedia.subscribe;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 订阅专辑标识
 *
 * @author 悭梵
 * @date Created in 2018-05-14 14:56
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@ApiModel(description = "订阅专辑DTO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlbumDto implements Serializable {
    @ApiModelProperty(value = "订阅专辑标识", name = "albumId", required = true, dataType = "string")
    private String albumId;
}
