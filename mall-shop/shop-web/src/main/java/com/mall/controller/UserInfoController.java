package com.mall.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.service.UserInfoService;
import com.mall.vo.UserInfoVO;
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

    @PostMapping("/insert")
    public int insert(@Validated({UserInfoVO.TestGroup.class}) @RequestBody UserInfoVO record) {

        log.info("请求参数 ----> {}", JSONObject.toJSONString(record));
        int id = userInfoService.insert(record);
        log.info("返回参数----> {}", id);
        return id;
    }

    @GetMapping("/query/{userId}")
    public UserInfoVO query(@PathVariable("userId")Integer userId) {

        log.info("请求参数----> {}", userId);
        UserInfoVO userVO = userInfoService.query(userId);
        log.info("返回参数 ----> {}", JSONObject.toJSONString(userVO));
        return userVO;
    }
}
