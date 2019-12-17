package com.mall.mapper;

import com.mall.dao.UserInfoDO;
import java.util.List;

public interface UserInfoMapper {

    /**
     *
     * @param userId
     * @return
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     *
     * @param record
     * @return
     */
    int insert(UserInfoDO record);

    /**
     *
     * @param userId
     * @return
     */
    UserInfoDO selectByPrimaryKey(Integer userId);

    /**
     *
     * @return
     */
    List<UserInfoDO> selectAll();

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserInfoDO record);

    /**
     * query all user info and user's role by user name
     * @param userName
     * @return
     */
    UserInfoDO selectAllUserInfoByUserName(String userName);
}
