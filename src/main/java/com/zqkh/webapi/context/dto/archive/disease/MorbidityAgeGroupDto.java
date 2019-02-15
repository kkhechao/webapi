package com.zqkh.webapi.context.dto.archive.disease;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 发病时间段
 *
 * @author 东来
 * @create 2018/6/26 0026
 */
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@ApiModel("发病时间段")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MorbidityAgeGroupDto implements Serializable {

    /**
     * 起始时间
     */
    @ApiModelProperty(value = "起始年龄", name = "start")
    private Integer start;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束年龄", name = "end")
    private Integer end;

}
