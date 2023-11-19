package com.andyron.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysOperLogQueryVo {
	@ApiModelProperty(value = "模块名称")
	private String title;

	@ApiModelProperty(value = "操作人名")
	private String operName;

	@ApiModelProperty(value = "开始时间")
	private String createTimeBegin;

	@ApiModelProperty(value = "结束时间")
	private String createTimeEnd;

}

