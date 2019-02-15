package com.zqkh.webapi.context.exception;

import com.jovezhao.nest.exception.CustomException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wenjie
 * @date 2017/12/8 0008 16:12
 */
@Getter
@Setter
public class BusinessException extends CustomException {

    public BusinessException(Integer code, String message, Object... params) {
        super(code, message, params);
    }
}
