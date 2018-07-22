package com.yinian.crawller.config;

import com.yinian.crawller.util.PropertiesUtil;

public class MonitorCfg {
	/**
	 * quartz执行时间设置
	 */
	public static final String QUARZ_CRON_EXPRESSION = PropertiesUtil.getDataParam("quarz_cron_expression");
	
}
