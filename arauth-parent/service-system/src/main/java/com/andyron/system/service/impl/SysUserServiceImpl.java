package com.andyron.system.service.impl;

import com.andyron.model.system.SysRole;
import com.andyron.model.system.SysUser;
import com.andyron.model.system.SysUserRole;
import com.andyron.model.vo.AssginRoleVo;
import com.andyron.model.vo.SysUserQueryVo;
import com.andyron.system.mapper.SysRoleMapper;
import com.andyron.system.mapper.SysUserMapper;
import com.andyron.system.mapper.SysUserRoleMapper;
import com.andyron.system.service.SysUserService;
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
@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;



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


}
