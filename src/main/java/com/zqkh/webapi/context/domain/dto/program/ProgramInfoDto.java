package com.zqkh.webapi.context.domain.dto.program;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 方案详情DTO
 *
 * @author 东来
 * @create 2018/6/6 0006
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "方案详情")
public class ProgramInfoDto implements Serializable {


    public enum Status {

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

        public static final Status getStatus(String status) {
            if (ObjectUtils.isEmpty(status)) {
                return null;
            }
            for (Status st : Status.values()) {
                if (status.equals(st.name())) {
                    return st;
                }
            }

            return null;
        }


    }

    /**
     * 编号
     */
    @ApiModelProperty(value = "方案编号", name = "id", dataType = "String", required = true)
    private String id;

    /**
     * 方案名称
     */
    @ApiModelProperty(value = "方案名称", name = "id", dataType = "String", required = true)
    private String name;

    /**
     * 试卷标题
     */
    @ApiModelProperty(value = "试卷标题", name = "testPaperTitle", dataType = "String", required = true)
    private String testPaperTitle;


    /**
     * 总天数
     */
    @ApiModelProperty(value = "总天数", name = "totalDay", dataType = "long", required = true)
    private long totalDay;


    /**
     * 已坚持多少天
     */
    @ApiModelProperty(value = "坚持天数", name = "insistDay", dataType = "long", required = true)
    private long insistDay;

    /**
     * 状态
     */
    @ApiModelProperty(value = "方案状态", name = "status", dataType = "Status", required = true)
    private Status status;

    /**
     * 任务
     */
    @ApiModelProperty(value = "任务", name = "task", dataType = "ProgramTaskListDto", required = true)
    private List<ProgramTaskListDto> task;

    /**
     * 方案结果编号
     */
    @ApiModelProperty(value = "方案结果编号", name = "programResultId", dataType = "String")
    private String programResultId;

}
