package com.andyron.system.service;

import com.andyron.model.system.SysRole;
import com.andyron.model.vo.AssginRoleVo;
import com.andyron.model.vo.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author andyron
 **/
public interface SysRoleService extends IService<SysRole> {
    IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo);

    Map<String, Object> getRolesByUserId(Long userId);

    void  doAssign(AssginRoleVo assginRoleVo);
}
