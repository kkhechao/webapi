package com.zqkh.webapi.context.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author wenjie
 * @date 2017/12/15 0015 10:01
 */
@Getter
@Setter
@AllArgsConstructor
public class ActionDto implements Serializable {

    public enum Method {
        GET,
        POST
    }

    private String rel;
    private String href;
    private Method method;
}
