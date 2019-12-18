package com.mall.mapper;

import com.github.pagehelper.Page;
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
     * query userInfo and wrap with page
     * @return
     */
    Page<UserInfoDO> selectUserInfoWithPage();

    /**
     * query all user info and user's role by user name
     * @param userName
     * @return
     */
    UserInfoDO selectAllUserInfoByUserName(String userName);
}
