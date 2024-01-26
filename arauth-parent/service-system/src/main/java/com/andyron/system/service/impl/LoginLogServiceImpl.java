package com.andyron.system.service.impl;

import com.andyron.model.system.SysLoginLog;
import com.andyron.model.system.SysOperLog;
import com.andyron.model.vo.SysLoginLogQueryVo;
import com.andyron.system.mapper.SysLoginLogMapper;
import com.andyron.system.service.LoginLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author andyron
 **/
@Service
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    SysLoginLogMapper loginLogMapper;
    @Override
    public void recordLoginLog(String username, Integer status, String ipaddr, String message) {
        SysLoginLog loginLog = new SysLoginLog();
        loginLog.setUsername(username);
        loginLog.setStatus(status);
        loginLog.setIpaddr(ipaddr);
        loginLog.setMsg(message);
        loginLogMapper.insert(loginLog);
    }

    @Override
    public IPage<SysLoginLog> selectPage(Long page, Long limit, SysLoginLogQueryVo sysLoginLogQueryVo) {
        Page<SysLoginLog> pageParam = new Page<>(page, limit);
        String username = sysLoginLogQueryVo.getUsername();
        String createTimeBegin = sysLoginLogQueryVo.getCreateTimeBegin();
        String createTimeEnd = sysLoginLogQueryVo.getCreateTimeEnd();
        QueryWrapper<SysLoginLog> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(username)) {
            wrapper.like("username", username);
        }
        if (!StringUtils.isEmpty(createTimeBegin)) {
            wrapper.ge("create_time", createTimeBegin);
        }
        if (!StringUtils.isEmpty(createTimeEnd)) {
            wrapper.le("create_time", createTimeEnd);
        }
        wrapper.orderByDesc("create_time");
        return loginLogMapper.selectPage(pageParam, wrapper);
    }

    @Override
    public SysLoginLog getById(Long id) {
        return loginLogMapper.selectById(id);
    }
}
