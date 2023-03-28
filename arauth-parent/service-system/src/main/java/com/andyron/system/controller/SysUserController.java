package com.andyron.system.controller;

import com.andyron.common.result.Result;
import com.andyron.model.system.SysUser;
import com.andyron.model.vo.AssginRoleVo;
import com.andyron.model.vo.SysUserQueryVo;
import com.andyron.system.service.SysUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author andyron
 **/
@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {
    @Autowired
    SysUserService sysUserService;

    @ApiOperation("获取用户")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable Long id) {
        SysUser sysUser = sysUserService.getById(id);
        return Result.ok(sysUser);
    }

    @ApiOperation("保存用户")
    @PostMapping("/save")
    public Result save(@RequestBody SysUser user) {
        sysUserService.save(user);
        return Result.ok();
    }

    @ApiOperation("更新用户")
    @PostMapping("/update")
    public Result update(@RequestBody SysUser user) {
        sysUserService.updateById(user);
        return Result.ok();
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Long id) {
        sysUserService.removeById(id);
        return Result.ok();
    }

    @ApiOperation("条件分页查询用户")
    @GetMapping("/{page}/{limit}")
    public Result findPageQueryUser(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "userQueryVo", value = "查询对象", required = false)
            SysUserQueryVo userQueryVo
    ) {
        Page<SysUser> pageParam = new Page<>(page, limit);
        IPage<SysUser> pageModel = sysUserService.selectPage(pageParam, userQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation("更新状态")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable int status) {
        sysUserService.updateStatus(id, status);
        return Result.ok();
    }


}
