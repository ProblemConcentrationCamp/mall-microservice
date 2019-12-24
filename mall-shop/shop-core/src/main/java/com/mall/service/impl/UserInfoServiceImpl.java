package com.mall.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mall.dao.UserInfoDO;
import com.mall.mapper.UserInfoMapper;
import com.mall.request.system.UserInfoRequest;
import com.mall.service.UserInfoService;
import com.mall.util.ResponseUtil;
import com.mall.web.response.Response;
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
    public Response<UserInfoDO> insert(UserInfoRequest record) {
        UserInfoDO userInfoDO = new UserInfoDO();
        BeanUtils.copyProperties(record, userInfoDO);
        userInfoMapper.insert(userInfoDO);
        return ResponseUtil.change2Response(userInfoDO);
    }

    @Override
    public Response<UserInfoDO> query(Integer userId) {
        UserInfoDO userInfoDO = userInfoMapper.selectByPrimaryKey(userId);
        return ResponseUtil.change2Response(userInfoDO);
    }

    @Override
    public Response<Page<UserInfoDO>> getUserPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<UserInfoDO> userInfoDOPage = userInfoMapper.selectUserInfoWithPage();
        return ResponseUtil.change2Response(userInfoDOPage);
    }
}
