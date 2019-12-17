package com.mall.mapper;

import com.mall.dao.RoleInfoDO;
import java.util.List;

public interface RoleInfoMapper {

    int deleteByPrimaryKey(Integer roleId);

    int insert(RoleInfoDO record);

    RoleInfoDO selectByPrimaryKey(Integer roleId);

    List<RoleInfoDO> selectAll();

    int updateByPrimaryKey(RoleInfoDO record);
}
