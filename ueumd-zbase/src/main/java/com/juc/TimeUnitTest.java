package com.juc;

import java.util.concurrent.TimeUnit;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-18 16:23
 */
public class TimeUnitTest {
    public static void main(String[] args) throws InterruptedException {

        // 获取cpu 核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        // 等待3秒
        TimeUnit.SECONDS.sleep(3);
        System.out.println("3秒");
    }
}
