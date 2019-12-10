package com.mall.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * <pre>
 * 用户信息业务对象
 * </pre>
 *
 * @author: LCN
 * @date: 2019-11-26 19:41
 */
@Data
@NoArgsConstructor
public class UserInfoVO {

    private Integer userId;

    @NotEmpty(message = "用户名不能为空", groups = TestGroup.class)
    private String userName;

    private String password;

    /** 测试组 */
    public @interface TestGroup{
    }
}
