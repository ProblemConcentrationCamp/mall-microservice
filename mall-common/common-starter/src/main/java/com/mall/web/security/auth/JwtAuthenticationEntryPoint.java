package com.mall.web.security.auth;

import com.alibaba.fastjson.JSONObject;
import com.mall.util.ResponseUtil;
import com.mall.web.constant.enums.RespCodeEnum;
import com.mall.web.response.Response;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 * the class defined to solve:
 * when user request a protected url, but the user is logout or the token expired
 * how to do
 * </pre>
 *
 * @author LCN
 * @date 2019-12-17 13:53
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException{
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        Response<Object> objectResponse = ResponseUtil.change2Response(RespCodeEnum.UNAUTHORIZED);
        httpServletResponse.getWriter().println(JSONObject.toJSONString(objectResponse));
    }
}
