package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-18 15:40
 */

// 线程池
public class TestThreadPool {
    public static void main(String[] args) {
        // 1. 创建服务
        ExecutorService service = Executors.newFixedThreadPool(10);

        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());

        // 2. 关闭链接
        service.shutdown();

    }
}

class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
