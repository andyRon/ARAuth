package com.andyron.system.service;

import com.andyron.model.system.SysUser;
import com.andyron.model.vo.AssginRoleVo;
import com.andyron.model.vo.SysUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author andyron
 **/
public interface SysUserService extends IService<SysUser> {
    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo userQueryVo);

    void updateStatus(Long id, int status);


}
