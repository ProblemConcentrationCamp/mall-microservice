package com.mall.service;


import com.github.pagehelper.Page;
import com.mall.dao.UserInfoDO;
import com.mall.request.system.UserInfoRequest;
import com.mall.web.response.Response;

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
    Response<UserInfoDO> insert(UserInfoRequest record);

    /**
     * 查询用户
     * @param userId
     * @return
     */
    Response<UserInfoDO> query(Integer userId);

    /**
     * 分页查询用户
     * @return
     */
    Response<Page<UserInfoDO>> getUserPage(int pageNum, int pageSize);
}
