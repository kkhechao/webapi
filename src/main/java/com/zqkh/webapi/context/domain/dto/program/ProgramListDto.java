package com.zqkh.webapi.context.domain.dto.program;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * 解决方案列表DTO
 *
 * @author 东来
 * @create 2018/6/6 0006
 */
@NoArgsConstructor
@Getter
@Setter
@ApiModel(description = "方案列表")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProgramListDto implements Serializable {
    @ApiModel(description = "方案状态")
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
     * 方案编号
     */
    @ApiModelProperty(value = "方案编号", name = "id", dataType = "String", required = true)
    private String id;

    /**
     * 方案名称
     */
    @ApiModelProperty(value = "方案名称", name = "name", dataType = "String", required = true)
    private String name;

    /**
     * 今日完成情况
     */
    @ApiModelProperty(value = "今日完成情况", name = "dayDone", dataType = "boolean", required = true)
    private Boolean dayDone = false;

    /**
     * 方案状态
     */
    @ApiModelProperty(value = "方案状态", name = "status", dataType = "Status", required = true)
    private Status status;


    /**
     * 坚持天数
     */
    @ApiModelProperty(value = "坚持天数", name = "insistDay", dataType = "long", required = true)
    private long insistDay = 0;

    /**
     * 方案结果编号
     */
    @ApiModelProperty(value = "方案结果编号", name = "programResultId", dataType = "String")
    private String programResultId;

}
