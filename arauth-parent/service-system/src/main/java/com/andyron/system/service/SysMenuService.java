package com.andyron.system.service;

import com.andyron.model.system.SysMenu;
import com.andyron.model.vo.AssginMenuVo;
import com.andyron.model.vo.RouterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author andyron
 **/
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 菜单树形数据
     */
    List<SysMenu> findNodes();

    /**
     * 根据角色获取授权权限数据
     */
    List<SysMenu> findSysMenuByRoleId(Long roleId);

    /**
     * 保存角色权限
     */
    void doAssign(AssginMenuVo assginMenuVo);

    /**
     * 获取用户菜单权限
     */
    List<RouterVo> findUserMenuList(Long userId);

    /**
     * 获取用户按钮权限
     */
    List<String> findUserPermsList(Long userId);
}
