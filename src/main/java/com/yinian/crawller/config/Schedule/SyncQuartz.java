package com.yinian.crawller.config.Schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SyncQuartz {
	private static final Logger LOGGER =  LoggerFactory.getLogger(SyncQuartz.class);
  	@Scheduled(cron = "0 0 0/1 * * ?") // 每小时执行一次
    public void work() throws Exception {
  		
    }
  	
  	@Scheduled(cron = "0 0 0/1 * * ?") // 每小时执行一次
    public void work1() throws Exception {
  		
  	}
}
