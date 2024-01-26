package com.andyron.model.vo;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author andyron
 */
@Data
public class SysLoginLogQueryVo {
	@ApiModelProperty(value = "用户账号")
	private String username;
	@ApiModelProperty(value = "开始时间")
	private String createTimeBegin;
	@ApiModelProperty(value = "结束时间")
	private String createTimeEnd;
}

