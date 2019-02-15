package com.zqkh.webapi.context.domain.dto.program;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 方案反馈DTO
 *
 * @author 东来
 * @create 2018/6/13 0013
 */
@ApiModel(description = "方案返回DTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ProgramFeedBackDto implements Serializable {

    /**
     * 消息提示
     */
    @ApiModelProperty(value = "消息提示", name = "message", dataType = "String", required = true)
    private String message;

}
