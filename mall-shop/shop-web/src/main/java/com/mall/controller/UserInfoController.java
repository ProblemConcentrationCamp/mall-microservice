package com.mall.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.dao.UserInfoDO;
import com.mall.request.system.UserInfoRequest;
import com.mall.service.UserInfoService;
import com.mall.web.response.Response;
import com.mall.web.validation.CommonCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <pre>
 *  用户信息控制类
 * </pre>
 *
 * @author: LCN
 * @date: 2019-11-14 14:18
 */
@Slf4j
@RestController
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @PostMapping("/register")
    public Response<UserInfoDO> register(@Validated({CommonCreate.class}) @RequestBody UserInfoRequest record) {

        return null;
    }

    @PostMapping("/insert")
    public Response<UserInfoDO> insert(@Validated({CommonCreate.class}) @RequestBody UserInfoRequest record) {

        log.info("请求参数 ----> {}", JSONObject.toJSONString(record));
        Response<UserInfoDO> response = userInfoService.insert(record);
        log.info("返回参数----> {}", JSONObject.toJSONString(response));
        return response;
    }

    @GetMapping("/query/{userId}")
    public Response<UserInfoDO> query(@PathVariable("userId")Integer userId) {

        log.info("请求参数----> {}", userId);
        Response<UserInfoDO> response = userInfoService.query(userId);
        log.info("返回参数 ----> {}", JSONObject.toJSONString(response));
        return response;
    }

    @GetMapping("/test")
    public String query() {
        return "query";
    }
}
