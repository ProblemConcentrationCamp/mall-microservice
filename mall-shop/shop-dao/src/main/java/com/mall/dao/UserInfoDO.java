package com.mall.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 *
 * </pre>
 *
 * @author LCN
 * @date 2019-12-10 23:18
 */

@Data
@NoArgsConstructor
public class UserInfoDO {
    private Integer userId;

    private String userName;

    private String password;
}
