package com.mall.web.execption;

import com.mall.util.ResponseUtil;
import com.mall.web.constant.enums.RespCodeEnum;
import com.mall.web.exception.BizException;
import com.mall.web.response.Response;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <pre>
 * the global handler to handler the all exception
 * </pre>
 *
 * @author LCN
 * @date 2019-12-12 23:03
 */

@Slf4j
@ResponseBody
@ControllerAdvice
@NoArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public Response<Object> exceptionHandler (Exception exception) {

        log.error("GlobalExceptionHandler --->", exception);

        // request args verity fail
        if (exception instanceof MethodArgumentNotValidException) {
            Map<String, String> map = new HashMap<>(16);
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException)exception;
            FieldError fieldError;
            Iterator iterator = ex.getBindingResult().getFieldErrors().iterator();
            while (iterator.hasNext()) {
                fieldError = (FieldError)iterator.next();
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return ResponseUtil.change2Response(map, RespCodeEnum.PARAMETER_ERROR);
        }

        // customized exception
        if (exception instanceof BizException) {
            BizException bizException = (BizException)exception;
            return ResponseUtil.change2Respnse(bizException.getMessage(),RespCodeEnum.CONDITIONS_NOT_MATCHED );
        }

        // unknown exception
        return ResponseUtil.change2Response(RespCodeEnum.FAILURE);
    }
}
