package com.andyron.system.utils;

import com.andyron.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 根据菜单数据构建菜单树的工具类根据菜单数据构建菜单树的工具类
 * @author andyron
 **/
public class MenuHelper {
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        List<SysMenu> trees = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            if (sysMenu.getParentId().longValue() == 0) {
                trees.add(findChildren(sysMenu, sysMenuList));
            }
        }
        return trees;
    }

    public static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> treeNodes) {
        sysMenu.setChildren(new ArrayList<>());

        for (SysMenu node : treeNodes) {
            if (sysMenu.getId().longValue() == node.getParentId().longValue()) {
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findChildren(node, treeNodes));
            }
        }
        return sysMenu;
    }
}
