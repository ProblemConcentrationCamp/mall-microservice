package com.mall.web.response;

import com.mall.web.constant.enums.RespCodeEnum;

/**
 * <pre>
 *
 * </pre>
 *
 * @author LCN
 * @date 2019-11-28 14:52
 */
public class Response<T> {

    private int code;

    private String msg;

    private T body;

    public Response() {
        this.code = RespCodeEnum.SUCCESS.getCode();
        this.msg = RespCodeEnum.SUCCESS.getMsg();
    }

    public Response(RespCodeEnum respCodeEnum) {
        this.code = respCodeEnum.getCode();
        this.msg = respCodeEnum.getMsg();
    }

    public Response(RespCodeEnum respCodeEnum, T body) {
        this.code = respCodeEnum.getCode();
        this.msg = respCodeEnum.getMsg();
        this.body = body;
    }

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(int code, String msg, T body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
