package com.zqkh.webapi.context.domain.vo.program;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 拷贝方案VO
 *
 * @author 东来
 * @create 2018/6/13 0013
 */
@NoArgsConstructor
@Getter
@Setter
@ApiModel(description = "拷贝方案")
public class CopyProgramVo implements Serializable {

    @ApiModelProperty(value = "方案编号", name = "id", dataType = "String", required = true)
    private String id;
    @ApiModelProperty(value = "家庭成员编号", name = "familyMemberId", dataType = "String", required = true)
    private String familyMemberId;
}
