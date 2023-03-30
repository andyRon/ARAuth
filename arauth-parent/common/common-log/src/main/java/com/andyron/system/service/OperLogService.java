package com.andyron.system.service;

import com.andyron.model.system.SysOperLog;
import com.andyron.model.vo.SysOperLogQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author andyron
 **/
public interface OperLogService {
    void saveSysLog(SysOperLog sysOperLog);

    /**
     * 操作日志分页查询
     */
    Page<SysOperLog> selectPage(Long page, Long limit, SysOperLogQueryVo sysOperLogQueryVo);
}
