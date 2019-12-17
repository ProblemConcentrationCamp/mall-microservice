package com.mall.service.impl.security;

import com.mall.dao.RoleInfoDO;
import com.mall.dao.UserInfoDO;
import com.mall.mapper.UserInfoMapper;
import com.mall.web.security.user.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <pre>
 * this jwt user service is only able to use in the security.
 *
 * if you have the same business, create another class please
 * </pre>
 *
 * @author LCN
 * @date 2019-12-17 11:44
 */

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Resource
    private UserInfoMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserInfoDO user = userMapper.selectAllUserInfoByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException(String.format(" User: %s no exist！", userName));
        }
        return createUserDetails(user);
    }

    /**
     * create jwt user by user info
     * @param user
     * @return
     */
    private UserDetails createUserDetails(UserInfoDO user) {
        return new JwtUser(
            user.getUserName(),
            user.getPassword(),
            user.getEnable(),
            user.getLastPasswordResetDate(),
            mapToGrantedAuthorities(user.getRoleSet()));
    }

    /**
     * change the role info 2 GrantedAuthority
     * @param userRoleSet
     * @return
     */
    private List<GrantedAuthority> mapToGrantedAuthorities(Set<RoleInfoDO> userRoleSet) {
        return userRoleSet.stream()
            .map(userRole -> new SimpleGrantedAuthority(userRole.getRoleName()))
            .collect(Collectors.toList());
    }

}
