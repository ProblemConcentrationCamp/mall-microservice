package com.mall.web.constant.enums;

/**
 * <pre>
 *  unified http response Code
 * </pre>
 *
 * @author LCN
 * @date 2019-11-28 14:45
 */
public enum RespCodeEnum {

    /**
     * request handler success
     */
    SUCCESS(200, "request success"),

    /**
     * the args in the request verify fail
     */
    PARAMETER_ERROR(400, "args in request verify fail"),

    /**
     * the user don't have the permission
     */
    UNAUTHORIZED(401, "no permission"),

    /**
     * the args can't matches the condition of handler business
     */
    CONDITIONS_NOT_MATCHED(412, "the args not matches the condition"),

    /**
     * the error system not undefined
     */
    FAILURE(-999, "unknown fail");

    private int code;
    private String msg;

    RespCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
