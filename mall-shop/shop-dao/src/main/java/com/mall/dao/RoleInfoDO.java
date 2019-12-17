package com.mall.dao;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleInfoDO {

    private Integer roleId;

    private String roleName;

    private String roleNameZh;
}
