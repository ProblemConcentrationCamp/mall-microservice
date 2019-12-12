package com.mall.web.advice;

import com.mall.util.ResponseUtil;
import com.mall.web.annotate.advice.IgnoreResponseAdvice;
import com.mall.web.constant.enums.RespCodeEnum;
import com.mall.web.response.Response;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * <pre>
 * make the return value of controller layout to Response<T>
 * </pre>
 *
 * @author LCN
 * @date 2019-12-12 22:57
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {

        // the method's class or the method have the IgnoreResponseAdvice annotate
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)
            || methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object responseDate, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (null == responseDate) {
            return ResponseUtil.change2Response(RespCodeEnum.SUCCESS);
        }
        if (responseDate instanceof Response) {
            return responseDate;
        }
        return ResponseUtil.change2Response(responseDate);
    }
}
