package com.zqkh.webapi.context.domain.dto.program;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 拷贝方案DTO
 *
 * @author 东来
 * @create 2018/6/13 0013
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "拷贝方案")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CopyProgramDto implements Serializable {

    /**
     * 方案编号
     */
    @ApiModelProperty(value = "方案编号", name = "id", dataType = "String", required = true)
    private String id;

}
