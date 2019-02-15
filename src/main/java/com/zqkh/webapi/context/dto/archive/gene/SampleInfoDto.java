package com.zqkh.webapi.context.dto.archive.gene;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 样本信息
 *
 * @author 东来
 * @create 2018/3/2 0002
 */
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "样本信息")
public class SampleInfoDto  implements Serializable{
    /**
     * 样本类型
     */
    @ApiModelProperty(value = "样本类型", name = "sampleType", required = true)
    private String sampleType;


    /**
     * 报告审核时间
     */
    @ApiModelProperty(value = "报告审核时间", name = "auditTime")
    private Date auditTime;
}
