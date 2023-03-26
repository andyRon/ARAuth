package com.andyron.system;

import com.andyron.model.system.SysRole;
import com.andyron.system.mapper.SysRoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author andyron
 **/
@SpringBootTest
public class SysRoleMapperTest {
    @Autowired
    SysRoleMapper sysRoleMapper;

    @Test
    public void testSelectList() {
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        for (SysRole sysRole : sysRoles) {
            System.out.println(sysRole);
        }
    }

    @Test
    public void testInsert(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("角色管理员");
        sysRole.setRoleCode("role");
        sysRole.setDescription("角色管理员");

        int result = sysRoleMapper.insert(sysRole);
        System.out.println(result); //影响的行数
        System.out.println(sysRole.getId()); //id自动回填
    }

    @Test
    public void testUpdateById(){
        SysRole sysRole = new SysRole();
        sysRole.setId(8L);
        sysRole.setDescription("用户管理员");

        int result = sysRoleMapper.updateById(sysRole);
        System.out.println(result);
    }
    @Test
    public void testDeleteById(){
        int result = sysRoleMapper.deleteById(2L);
        System.out.println(result);
    }
    @Test
    public void testDeleteBatchIds() {
        int result = sysRoleMapper.deleteBatchIds(Arrays.asList(1, 2));
        System.out.println(result);
    }

    @Test
    public void testQueryWrapper() {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
//        queryWrapper.ge("role_code", "role");
        queryWrapper.like("description", "管理员");
        List<SysRole> users = sysRoleMapper.selectList(queryWrapper);
        System.out.println(users);
    }
}
