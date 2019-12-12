package com.mall.util;

import com.mall.web.constant.enums.RespCodeEnum;
import com.mall.web.response.Response;

import java.util.HashMap;
import java.util.Map;
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
     * the key of response body content
     */
    private final static String DEFAULT_RESPONSE_STRING = "result";

    /**
     * response without any body msg
     * @param respCodeEnum
     * @return
     */
    public static Response<Object> change2Respnse(RespCodeEnum respCodeEnum) {
        Response<Object> response = new Response(respCodeEnum);
        return response;
    }

    /**
     * success response
     * @param data
     * @return
     */
    public static Response<Object> change2Response(Object data) {
        Response<Object> response = new Response<>();
        return responseBodyHandler(response,data);
    }

    /**
     * custom response code and message
     * @param data
     * @param respCodeEnum
     * @return
     */
    public static Response<Object> change2Response(Object data, RespCodeEnum respCodeEnum) {
        Response<Object> response = new Response<>(respCodeEnum);
        return responseBodyHandler(response,data);
    }

    /**
     * custom response code and message
     * @param data
     * @param code
     * @param msg
     * @return
     */
    public static Response<Object> change2Respnse(Object data, int code, String msg) {
        Response<Object> response = new Response<>(code, msg, data);
        return responseBodyHandler(response,data);
    }

    /**
     * response body handler
     * @param response
     * @param data
     * @return
     */
    private static Response<Object> responseBodyHandler(Response<Object> response, Object data) {
        if (Objects.isNull(data)) {
            return response;
        }
        if (ClassUtil.isBaseType(data.getClass())) {
            Map<String, Object> map = new HashMap<>(2);
            map.put(DEFAULT_RESPONSE_STRING, data);
            response.setBody(map);
            return response;
        }
        response.setBody(data);
        return response;
    }
}