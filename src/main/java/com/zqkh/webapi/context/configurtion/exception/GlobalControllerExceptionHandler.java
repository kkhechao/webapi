package com.zqkh.webapi.context.configurtion.exception;

import com.jovezhao.nest.exception.SystemException;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.exception.BusinessException;
import feign.FeignException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaofujun on 2017/8/28.
 */
@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = {BusinessException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse constraintBusinessException(BusinessException ex) {
        log.error("Business exception, errorCode: {}, errorDesc: {}", ex.getErrorCode(), ex.getMessage());
        return new ApiErrorResponse(ex.getErrorCode(), ex.getMessage(), ex);
    }

    @ExceptionHandler(value = {SystemException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse constraintSystemException(SystemException ex) {
        log.error("System exception, errorDesc: {}", ex.getCause().getMessage());
        return new ApiErrorResponse(500, ex.getMessage(), ex);
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse constraintException(Exception ex) {
        log.error("Unknow exception", ex);
        return new ApiErrorResponse(500, ex.getMessage(), ex);
    }

    @ExceptionHandler(value = {FeignException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse constraintFeignException(Exception ex) {
        log.error("Feign exception", ex);
        return new ApiErrorResponse(BusinessExceptionEnum.FEIGN_EXCEPTION.getCode(), ex.getMessage(), ex);
    }

    /**
     * Handle violation exception
     * 验证异常处理message提示
     *
     * @param cve
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse error(ConstraintViolationException cve) {
        log.error("Params violation excetion", cve);

        Set<ConstraintViolation<?>> constraintViolations = cve.getConstraintViolations();
        List<String> errorMsg = new LinkedList<>();
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            for (ConstraintViolation<?> violation : constraintViolations) {
                errorMsg.add(violation.getMessage());
            }
        }
        return new ApiErrorResponse(BusinessExceptionEnum.INVALID_PARAMS.getCode(), errorMsg.toString(), cve);
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse error(BindException bindException) {
        log.error("Params bind exception", bindException);
        List<String> errorMsg = new LinkedList<>();
        for (ObjectError objectError : bindException.getAllErrors()) {
            FieldError fieldError = (FieldError) objectError;
            errorMsg.add(fieldError.getField() + fieldError.getDefaultMessage());
        }
        return new ApiErrorResponse(BusinessExceptionEnum.INVALID_PARAMS.getCode(), errorMsg.toString(), bindException);
    }

    /**
     * @param ex
     * @return
     * @RequestBody json数据接收绑定异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String errorMessage = fieldError.getField() + fieldError.getDefaultMessage();
        return new ApiErrorResponse(BusinessExceptionEnum.INVALID_PARAMS.getCode(), errorMessage, ex);
    }

}

@Getter
@Setter
class ApiErrorResponse {

    private int code;
    private String message;
    private String[] exception;


    public ApiErrorResponse(int code, String message, Exception exception) {

        this.code = code;
        this.message = message;

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        pw.close();
        this.exception = sw.toString().split("\n");
    }
}
