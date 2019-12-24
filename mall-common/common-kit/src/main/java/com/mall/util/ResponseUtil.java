package com.mall.util;

import com.mall.web.constant.enums.RespCodeEnum;
import com.mall.web.response.Response;

import java.util.Objects;

/**
 * <pre>
 * http response Util
 * </pre>
 *
 * @author LCN
 * @date 2019-12-12 22:50
 */
public class ResponseUtil {

    /**
     * response without any body msg
     * @param respCodeEnum
     * @return
     */
    public static Response<Object> change2Response(RespCodeEnum respCodeEnum) {
        Response<Object> response = new Response(respCodeEnum);
        return response;
    }

    /**
     * success response
     * @param data
     * @return
     */
    public static <T> Response<T> change2Response(T data) {
        return change2Response(data, RespCodeEnum.SUCCESS);
    }

    /**
     * response with the body
     * @param data
     * @param respCodeEnum
     * @return
     */
    public static <T> Response<T> change2Response(T data, RespCodeEnum respCodeEnum) {
        Response<T> response = new Response<>();
        if (Objects.isNull(data)) {
            return response;
        }
        response.setBody(data);
        return response;
    }
}
