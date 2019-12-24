package com.mall.web.security.auth;

import com.alibaba.fastjson.JSONObject;
import com.mall.util.ResponseUtil;
import com.mall.web.constant.enums.RespCodeEnum;
import com.mall.web.response.Response;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 * the class defined to solve:
 * when user request a protected url, but the user don't have permission
 * how to do
 * </pre>
 *
 * @author LCN
 * @date 2019-12-20 15:32
 */
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException e) throws IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Response<Object> objectResponse = ResponseUtil.change2Response(RespCodeEnum.NO_PERMISSION);
        response.getWriter().println(JSONObject.toJSONString(objectResponse));
    }
}
