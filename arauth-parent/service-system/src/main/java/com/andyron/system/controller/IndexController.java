package com.andyron.system.controller;


import com.andyron.common.result.Result;
import com.andyron.common.utils.JwtHelper;
import com.andyron.common.utils.MD5;
import com.andyron.model.system.SysUser;
import com.andyron.model.vo.LoginVo;
import com.andyron.system.exception.ARException;
import com.andyron.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户登录接口")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;
    
    /**
     * {"code":20000,"data":{"token":"admin-token"}}
     * @param loginVo
     * @return
     */
    @ApiOperation("登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) {
        // 根据username查询数据
        SysUser sysUser = sysUserService.getUserInfoByUserName(loginVo.getUsername());
        // 如果查询为空
        if(sysUser == null) {
            throw new ARException(20001,"用户不存在");
        }
        // 判断密码是否一致
        String password = loginVo.getPassword();
        String md5Password = MD5.encrypt(password);
        if(!sysUser.getPassword().equals(md5Password)) {
            throw new ARException(20001,"密码不正确");
        }
        // 判断用户是否可用
        if(sysUser.getStatus().intValue() == 0) {
            throw new ARException(20001,"用户已经被禁用");
        }
        // 根据userid和username生成token字符串，通过map返回
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        return Result.ok(map);
    }

    /**
     * {"code":20000,"data":{"roles":["admin"],
     *         "introduction":"I am a super administrator",
     *                 "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
     *                 "name":"Super Admin"}}
     * @param request
     * @return
     */
    @ApiOperation("获取用户信息")
    @GetMapping("/info")
    public Result info(HttpServletRequest request) {
        String username = JwtHelper.getUsername(request.getHeader("token"));
        // 根据用户名称获取用户信息（基本信息 和 菜单权限 和 按钮权限数据）
        Map<String,Object> map = sysUserService.getUserInfo(username);
        return Result.ok(map);
    }

    @PostMapping("logout")
    public Result logout() {
        return Result.ok();
    }

}
