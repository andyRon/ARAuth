package com.andyron.system.service.impl;

import com.andyron.common.result.ResultCodeEnum;
import com.andyron.model.system.SysMenu;
import com.andyron.model.system.SysRoleMenu;
import com.andyron.model.vo.AssginMenuVo;
import com.andyron.model.vo.RouterVo;
import com.andyron.system.exception.ARException;
import com.andyron.system.mapper.SysMenuMapper;
import com.andyron.system.mapper.SysRoleMenuMapper;
import com.andyron.system.service.SysMenuService;
import com.andyron.system.utils.MenuHelper;
import com.andyron.system.utils.RouterHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author andyron
 **/
@Service
@Transactional // 🔖
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;
    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> findNodes() {
        List<SysMenu> list = this.list();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return MenuHelper.buildTree(list);
    }

    @Override
    public List<SysMenu> findSysMenuByRoleId(Long roleId) {
        // 获取所有status为1的权限菜单
        List<SysMenu> menuList = sysMenuMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1));
        // 获取角色权限
        List<SysRoleMenu> roleMenus = sysRoleMenuMapper.selectList(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
        List<Long> roleMenuIds = new ArrayList<>();
        for (SysRoleMenu roleMenu : roleMenus) {
            roleMenuIds.add(roleMenu.getMenuId());
        }
        //
        for (SysMenu sysMenu : menuList) {
            if (roleMenuIds.contains(sysMenu.getId())) {
                sysMenu.setSelect(true);
            } else {
                sysMenu.setSelect(false);
            }
        }

        return MenuHelper.buildTree(menuList);
    }

    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        sysRoleMenuMapper.delete(new QueryWrapper<SysRoleMenu>().eq("role_id", assginMenuVo.getRoleId()));

        for (Long menuId : assginMenuVo.getMenuIdList()) {
            if (menuId != null) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
                sysRoleMenuMapper.insert(sysRoleMenu);
            }
        }
    }

    @Override
    public boolean removeById(Serializable id) {
        int count = this.count(new QueryWrapper<SysMenu>().eq("parent_id", id));
        if (count > 0) {
            throw new ARException(ResultCodeEnum.NODE_ERROR);
        }
        sysMenuMapper.deleteById(id);
        return false;
    }

    @Override
    public List<RouterVo> findUserMenuList(Long userId) {
        List<SysMenu> menuList = null;
        if (userId.longValue() == 1) {
            menuList = sysMenuMapper.selectList(
                    new QueryWrapper<SysMenu>().eq("status", 1).orderByAsc("sort_value"));
        } else {
            menuList = sysMenuMapper.findListByUserId(userId);
        }
        return RouterHelper.buildRouters(MenuHelper.buildTree(menuList));
    }

    @Override
    public List<String> findUserPermsList(Long userId) {
        List<SysMenu> menuList = null;
        if (userId.longValue() == 1) {
            menuList = sysMenuMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1));
        } else {
            menuList = sysMenuMapper.findListByUserId(userId);
        }
        //创建返回的集合
        List<String> permissionList = new ArrayList<>();
        for (SysMenu sysMenu : menuList) {
            if(sysMenu.getType() == 2){
                permissionList.add(sysMenu.getPerms());
            }
        }
        return permissionList;
    }
}
