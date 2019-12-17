package com.mall.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserInfoDO {

    private Integer userId;

    private String userName;

    private String password;

    private String eMail;

    private String headPortraits;

    private String introduce;

    private Boolean sex;

    private Date lastLoginDate;

    private Date birthday;

    private Date lastPasswordResetDate;

    private Date registrationDate;

    private Boolean enable;

    private Boolean locked;

    /**
     * the user have the roles
     */
    private Set<RoleInfoDO> roleSet;

}
