package com.andyron.system.service.impl;

import com.andyron.model.system.SysUser;
import com.andyron.system.custom.CustomUser;
import com.andyron.system.service.SysMenuService;
import com.andyron.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * spring security中根据用户名称查询用户信息
 * @author andyron
 **/
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getUserInfoByUserName(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (sysUser.getStatus().intValue() == 0) {
            throw new RuntimeException("用户被禁用了");
        }
        // 根据userId查询操作权限数据
        List<String> userPermsList = sysMenuService.findUserPermsList(sysUser.getId());
        // 转换为spring Security要求的格式数据
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String perm : userPermsList) {
            authorities.add(new SimpleGrantedAuthority(perm.trim()));
        }
        return new CustomUser(sysUser, authorities);
    }
}
