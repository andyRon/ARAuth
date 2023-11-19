package com.andyron.system.service;

import com.andyron.model.system.SysLoginLog;
import com.andyron.model.vo.SysLoginLogQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 日志服务
 * @author andyron
 **/
public interface LoginLogService {

    /**
     * 登录日志
     * @param username
     * @param status
     * @param ipaddr
     * @param message
     */
    void recordLoginLog(String username, Integer status, String ipaddr, String message);

    IPage<SysLoginLog> selectPage(Long page, Long limit, SysLoginLogQueryVo sysLoginLogQueryVo);

    SysLoginLog getById(Long id);
}
