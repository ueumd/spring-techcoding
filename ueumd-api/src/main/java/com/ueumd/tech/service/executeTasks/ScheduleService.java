package com.ueumd.tech.service.executeTasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Description: 定时任务
 * Author: hsd
 * Date: 2023-06-17 16:56
 */
@Service
public class ScheduleService {

    /**
     * cron 表达式
     * https://www.cnblogs.com/cuiqq/p/10961478.html
     * https://cron.qqe2.com/
     * 秒 分 时 日 月 日 周
     */

    // 17:02:00 执行了这个任务
    @Scheduled(cron = "0 2 17 * * ?")
    public void taskOne() {
        System.out.println("=========在某个时间点执行了这个任务");
    }
}
