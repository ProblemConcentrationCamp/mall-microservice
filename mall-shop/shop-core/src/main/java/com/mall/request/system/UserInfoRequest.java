package com.mall.request.system;

import com.mall.web.validation.CommonCreate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * <pre>
 *
 * </pre>
 *
 * @author LCN
 * @date 2019-12-20 17:20
 */
public class UserInfoRequest {

    private Integer userId;

    @NotEmpty(message = "用户名不能为空", groups = CommonCreate.class)
    private String userName;

    @NotEmpty(message = "密码不能为空", groups = CommonCreate.class)
    private String password;

    @NotBlank(message = "邮箱不能为空", groups = CommonCreate.class)
    private String eMail;
}
