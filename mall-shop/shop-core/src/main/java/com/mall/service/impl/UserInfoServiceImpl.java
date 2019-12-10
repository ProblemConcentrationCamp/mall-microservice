package com.mall.service.impl;


import com.mall.dao.UserInfoDO;
import com.mall.mapper.UserMapper;
import com.mall.service.UserInfoService;
import com.mall.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <pre>
 * 用户信息接口实现类
 * </pre>
 *
 * @author: LCN
 * @date: 2019-11-26 19:42
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int insert(UserInfoVO record) {
        UserInfoDO userInfoDO = new UserInfoDO();
        BeanUtils.copyProperties(record, userInfoDO);
        userMapper.insert(userInfoDO);
        return 1;
    }

    @Override
    public UserInfoVO query(Integer userId) {

        UserInfoDO userPO = userMapper.query(userId);
        UserInfoVO userInfoBO = new UserInfoVO();
        BeanUtils.copyProperties(userPO, userInfoBO);
        return userInfoBO;
    }
}
