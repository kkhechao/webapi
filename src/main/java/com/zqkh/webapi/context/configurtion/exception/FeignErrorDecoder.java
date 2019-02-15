package com.zqkh.webapi.context.configurtion.exception;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jovezhao.nest.exception.SystemException;
import com.zqkh.webapi.context.exception.BusinessException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Map;

/**
 * @author wenjie
 * @date 2017/12/12 0012 15:43
 * 异常传递， 微服务中抛出的异常restful始终是以json传输的，所以需要将json转换成真正的异常信息才能走异常aop
 */
@Configuration
@Slf4j
public class FeignErrorDecoder implements feign.codec.ErrorDecoder {

    public static final String BUSINESS_EXCEPTION_STR = "BusinessException";
    public static final String SYSTEM_EXCEPTION_STR = "SystemException";

    private ErrorDecoder delegate = new ErrorDecoder.Default();

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {

//        HttpHeaders responseHeaders = new HttpHeaders();
//        for (Map.Entry<String, Collection<String>> entry : response.headers().entrySet()) {
//            responseHeaders.put(entry.getKey(), new ArrayList<>(entry.getValue()));
//        }

        try {
            String body = Util.toString(response.body().asReader());
            Map<String, String> map = mapper.readValue(body, new TypeReference<Map<String, String>>() {
            });

            String clazzName = map.get("clazz");
            //转换微服务中自定义异常
            if (StringUtils.contains(clazzName, BUSINESS_EXCEPTION_STR)) {
                return new BusinessException(Integer.valueOf(map.get("code")), map.get("message"));
            } else if (StringUtils.contains(clazzName, SYSTEM_EXCEPTION_STR)) {
                return new SystemException(map.get("message"), new Throwable(map.get("cause")));
            } else {
                log.error(map.get("clazz"));
                log.error(map.get("cause"));
            }
        } catch (IOException e) {
            throw new SystemException(HttpStatus.INTERNAL_SERVER_ERROR + "", e);
        }
        System.out.println(methodKey + " response: " + response);
        return delegate.decode(methodKey, response);
    }
}
