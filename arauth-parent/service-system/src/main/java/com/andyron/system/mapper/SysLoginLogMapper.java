package com.andyron.system.mapper;

import com.andyron.model.system.SysLoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author andyron
 **/
@Repository
@Mapper
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {
}
