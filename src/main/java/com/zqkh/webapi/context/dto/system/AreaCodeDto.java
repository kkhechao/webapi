package com.zqkh.webapi.context.dto.system;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author hty
 * @create 2017-12-12 18:04
 **/
@Getter
@Setter
public class AreaCodeDto implements Serializable {

    private String code;

    private String name;

}
