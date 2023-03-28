package com.andyron.system.controller;

import com.andyron.common.result.Result;
import com.andyron.model.system.SysRole;
import com.andyron.model.vo.AssginRoleVo;
import com.andyron.model.vo.SysRoleQueryVo;
import com.andyron.system.exception.ARException;
import com.andyron.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author andyron
 **/
@Api(tags = "角色管理")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation(value = "获取全部角色")
    @GetMapping("/findAll")
    public Result<List<SysRole>> findAll() {
        List<SysRole> roleList = sysRoleService.list();
        return Result.ok(roleList);
    }

    @ApiOperation(value = "获取角色")
    @GetMapping("/findRoleById/{id}")
    public Result findRoleById(@PathVariable Long id) {
        SysRole role = sysRoleService.getById(id);
        return Result.ok(role);
    }

    /**
     * @RequestBody 不能使用get提交方式，传递json格式数据，把json格式数据封装到对象里面{...}
     */
    @ApiOperation(value = "新增角色")
    @PostMapping("/save")
    public Result save(@RequestBody SysRole role) {
        boolean isSuccess = sysRoleService.save(role);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }

    }

    @ApiOperation(value = "修改角色")
    @PutMapping("/update")
    public Result updateById(@RequestBody SysRole role) {
        sysRoleService.updateById(role);
        return Result.ok();
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Long id) {
        sysRoleService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "条件分页查询")
    @GetMapping("/{page}/{limit}")
    public Result findPageQueryRole(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "roleQueryVo", value = "查询对象", required = false)
            SysRoleQueryVo sysRoleQueryVo) {

        Page<SysRole> pageParam = new Page<>(page, limit);
        IPage<SysRole> pageModel = sysRoleService.selectPage(pageParam, sysRoleQueryVo);
        return Result.ok(pageModel);
    }

    /**
     * json数组格式对应java的list集合
     */
    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids) {
        sysRoleService.removeByIds(ids);
        return Result.ok();
    }

    @ApiOperation("根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable Long userId) {
        Map<String, Object> roleMap = sysRoleService.getRolesByUserId(userId);
        return Result.ok(roleMap);
    }

    @ApiOperation("根据用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }
}
