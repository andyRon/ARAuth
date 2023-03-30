package com.andyron.system.controller;

import com.andyron.common.result.Result;
import com.andyron.model.system.SysOperLog;
import com.andyron.model.system.SysRole;
import com.andyron.model.vo.SysOperLogQueryVo;
import com.andyron.system.service.LoginLogService;
import com.andyron.system.service.OperLogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author andyron
 **/
@Api(tags = "操作日志管理", value = "操作日志管理")
@RestController
// 🔖 value?
@RequestMapping(value = "/admin/system/sysOperLog")
public class SysOperLogController {
    @Autowired
    private OperLogService operLogService;

    @ApiOperation(value = "条件分页查询")
    @GetMapping("/{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "sysOperLogVo", value = "查询对象", required = false)
            SysOperLogQueryVo sysOperLogQueryVo) {
        IPage<SysOperLog> pageModel = operLogService.selectPage(page, limit, sysOperLogQueryVo);
        return Result.ok(pageModel);
    }
}
