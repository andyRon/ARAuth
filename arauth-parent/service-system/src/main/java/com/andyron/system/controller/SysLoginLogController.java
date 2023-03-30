package com.andyron.system.controller;

import com.andyron.common.result.Result;
import com.andyron.model.system.SysLoginLog;
import com.andyron.model.vo.SysLoginLogQueryVo;
import com.andyron.system.service.LoginLogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
@Api(tags = "登录日志管理", value = "登录日志管理")
@RestController
@RequestMapping(value = "/admin/system/sysLoginLog")
public class SysLoginLogController {
    @Autowired
    private LoginLogService loginLogService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "sysLoginLogVo", value = "查询对象", required = false)
            SysLoginLogQueryVo sysLoginLogQueryVo) {
        IPage<SysLoginLog> pageModel = loginLogService.selectPage(page, limit, sysLoginLogQueryVo);
        return Result.ok(pageModel);
    }
}
