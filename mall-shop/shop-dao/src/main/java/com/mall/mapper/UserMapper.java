package com.mall.mapper;

import com.mall.dao.UserInfoDO;
import org.apache.ibatis.annotations.Param;

/**
 * <pre>
 *
 * </pre>
 *
 * @author: LCN
 * @date: 2019-11-14 14:14
 */
public interface UserMapper {

    /**
     * 新增用户信息
     * @param record
     * @return
     */
    int insert(UserInfoDO record);

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    UserInfoDO query(@Param("userId") Integer userId);

}
