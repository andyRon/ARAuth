package com.andyron.system.service.impl;

import com.andyron.model.system.SysRole;
import com.andyron.model.system.SysUser;
import com.andyron.model.system.SysUserRole;
import com.andyron.model.vo.AssginRoleVo;
import com.andyron.model.vo.RouterVo;
import com.andyron.model.vo.SysUserQueryVo;
import com.andyron.system.mapper.SysRoleMapper;
import com.andyron.system.mapper.SysUserMapper;
import com.andyron.system.mapper.SysUserRoleMapper;
import com.andyron.system.service.SysMenuService;
import com.andyron.system.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author andyron
 **/
@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysMenuService sysMenuService;


    @Override
    public IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo userQueryVo) {
        IPage<SysUser> pageModel = baseMapper.selectPage(pageParam, userQueryVo);
        return pageModel;
    }

    @Override
    public void updateStatus(Long id, int status) {
        SysUser sysUser = sysUserMapper.selectById(id);
        sysUser.setStatus(status);
        sysUserMapper.updateById(sysUser);
    }

    @Override
    public SysUser getUserInfoByUserName(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public Map<String, Object> getUserInfo(String username) {
        Map<String, Object> res = new HashMap<>();
        SysUser sysUser = getUserInfoByUserName(username);
        List<RouterVo> routerVoList = sysMenuService.findUserMenuList(sysUser.getId());
        List<String> permsList = sysMenuService.findUserPermsList(sysUser.getId());
        res.put("name", sysUser.getName());
//        res.put("avatar", sysUser.getHeadUrl());
        // 🔖
        res.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        res.put("roles", new HashSet<>());
        res.put("buttons", permsList);
        res.put("routers", routerVoList);
        return res;
    }


}
