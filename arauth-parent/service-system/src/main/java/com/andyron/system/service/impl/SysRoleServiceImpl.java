package com.andyron.system.service.impl;

import com.andyron.model.system.SysRole;
import com.andyron.model.system.SysUserRole;
import com.andyron.model.vo.AssginRoleVo;
import com.andyron.model.vo.SysRoleQueryVo;
import com.andyron.system.mapper.SysRoleMapper;
import com.andyron.system.mapper.SysUserRoleMapper;
import com.andyron.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author andyron
 **/
@Transactional
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo) {
        IPage<SysRole> pageModel = baseMapper.selectPage(pageParam, sysRoleQueryVo);
        return pageModel;
    }

    @Override
    public Map<String, Object> getRolesByUserId(Long userId) {
        // 所有角色
        List<SysRole> roles = sysRoleMapper.selectList(null);
        // 获取用户角色
        QueryWrapper<SysUserRole> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        List<SysUserRole> userRoles = sysUserRoleMapper.selectList(qw);
        // 获取用户的角色id
        List<Long> userRoleIds = new ArrayList<>();
        for (SysUserRole role : userRoles) {
            userRoleIds.add(role.getRoleId());
        }

        Map<String, Object> res = new HashMap<>();
        res.put("allRoles", roles);
        res.put("userRoleIds", userRoleIds);
        return res;
    }

    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        // 先删除原来用户的角色 🔖
        QueryWrapper<SysUserRole> qw = new QueryWrapper<>();
        qw.eq("user_id", assginRoleVo.getUserId());
        sysUserRoleMapper.delete(qw);

        // 在添加新的角色
        List<Long> roleIdList = assginRoleVo.getRoleIdList();
        for (Long roleId : roleIdList) {
            if (roleId != null) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(assginRoleVo.getUserId());
                sysUserRole.setRoleId(roleId);
                sysUserRoleMapper.insert(sysUserRole);
            }
        }
    }
}
