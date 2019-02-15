package com.zqkh.webapi.context.dto.healthy.test.paper;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * 答题结果指标DTO
 *
 * @author 东来
 * @create 2018/5/10 0010
 */
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("测试结果指标")
public class TestResultPointDto implements Serializable {
    /**
     * 方案状态
     */
    @ApiModel(description = "方案状态")
    public enum ProgramStatus {

        /**
         * 接收
         */
        RECEIVE,

        /**
         * 拒绝接收时间
         */
        REJECT,
        /**
         * 开启
         */
        OPEN,

        /**
         * 结束
         */
        END,;

        public static final TestResultPointDto.ProgramStatus getStatus(String status) {
            if (ObjectUtils.isEmpty(status)) {
                return null;
            }
            for (TestResultPointDto.ProgramStatus st : TestResultPointDto.ProgramStatus.values()) {
                if (status.equals(st.name())) {
                    return st;
                }
            }

            return null;
        }


    }

    /**
     * 指标名
     */
    @ApiModelProperty(value = "指标名", name = "name", dataType = "String", required = true)
    private String name;

    /**
     * 背景图
     */
    @ApiModelProperty(value = "背景图", name = "coverUrl", dataType = "String", required = true)
    private String coverUrl;

    /**
     * 指标评分结果
     */
    @ApiModelProperty(value = "指标评分结果", name = "result", dataType = "String", required = true)
    private String result;

    /**
     * 结果解释
     */
    @ApiModelProperty(value = "结果解释", name = "explain", dataType = "String", required = true)
    private String explain;

    /**
     * 解决方案编号
     */
    @ApiModelProperty(value = "解决方案编号", name = "programId", dataType = "String", required = true)
    private String programId;

    /**
     * 方案状态
     */
    @ApiModelProperty(value = "方案状态", name = "ProgramStatus", dataType = "String", required = true)
    private ProgramStatus programStatus;

    /**
     * 方案结果编号
     */
    @ApiModelProperty(value = "方案结果编号", name = "programResultId", dataType = "String")
    private String programResultId;

}
