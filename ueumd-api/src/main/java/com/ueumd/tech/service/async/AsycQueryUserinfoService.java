package com.ueumd.tech.service.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-17 16:37
 */
@Service
public class AsycQueryUserinfoService {
    @Async
    public void selectUserinfo() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("异处数据据处理完毕");
    }
}
