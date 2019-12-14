package com.mall.web.exception;

/**
 * <pre>
 * Business exception
 * </pre>
 *
 * @author LCN
 * @date 2019-12-14 21:37
 */
public class BizException extends RuntimeException {

    public BizException() {
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

}
