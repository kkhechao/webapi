package com.zqkh.webapi.context.dto.system;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统设置Dto对象
 *
 * @author 悭梵
 * @date Created in 2018-06-05 14:33
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class SettingDto implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 所属系统，为空表示对所有系统有效
     */
    private String appId;
    /**
     * 名称/用途
     */
    private String name;
    /**
     * 键
     */
    private String key;
    /**
     * 值
     */
    private String value;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
}
