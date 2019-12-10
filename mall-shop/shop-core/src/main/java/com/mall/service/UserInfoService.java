package com.mall.service;


import com.mall.vo.UserInfoVO;

/**
 * <pre>
 * 用户信息接口
 * </pre>
 *
 * @author: LCN
 * @date: 2019-11-26 19:42
 */
public interface UserInfoService {

    /**
     * 新增用户
     * @param record
     * @return
     */
    int insert(UserInfoVO record);

    /**
     * 查询用户
     * @param userId
     * @return
     */
    UserInfoVO query(Integer userId);
}
