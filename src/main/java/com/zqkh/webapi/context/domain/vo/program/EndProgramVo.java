package com.zqkh.webapi.context.domain.vo.program;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 结束方案
 *
 * @author 东来
 * @create 2018/6/12 0012
 */
@ApiModel(description = "结束方案")
@NoArgsConstructor
@Getter
@Setter
public class EndProgramVo implements Serializable {

    /**
     * 方案编号
     */
    @ApiModelProperty(value = "方案编号", name = "id", dataType = "String", required = true)
    private String id;
    /**
     * 家庭成员编号
     */
    @ApiModelProperty(value = "家庭成员编号", name = "familyMemberId", dataType = "String", required = true)
    private String familyMemberId;

}
