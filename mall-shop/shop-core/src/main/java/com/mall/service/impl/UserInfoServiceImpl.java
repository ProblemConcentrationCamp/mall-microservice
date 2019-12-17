package com.mall.service.impl;


import com.mall.dao.UserInfoDO;
import com.mall.mapper.UserInfoMapper;
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
    private UserInfoMapper userInfoMapper;

    @Override
    public int insert(UserInfoVO record) {

        UserInfoDO userInfoDO = new UserInfoDO();
        BeanUtils.copyProperties(record, userInfoDO);
        return userInfoMapper.insert(userInfoDO);
    }

    @Override
    public UserInfoVO query(Integer userId) {

        UserInfoDO userInfoDO = userInfoMapper.selectByPrimaryKey(userId);
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfoDO, userInfoVO);
        return userInfoVO;
    }
}
