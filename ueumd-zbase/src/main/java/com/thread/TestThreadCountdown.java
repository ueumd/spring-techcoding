package com.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-18 9:35
 */
public class TestThreadCountdown {
    public static void main(String[] args) throws InterruptedException {

        // countdown();

        // 模拟打印系统时间
        Date startTime = new Date(System.currentTimeMillis()); // 当前系统时间
        System.out.println(startTime);

        while (true) {
            Thread.sleep(1000);
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(startTime));
            startTime = new Date(System.currentTimeMillis());
        }


    }

    // 倒计时
    public static void countdown() throws InterruptedException {
        int num = 10;
        while (true) {
            Thread.sleep(1000);
            System.out.println(num--);
            if (num <=0) {
                break;
            }
        }
    }
}
