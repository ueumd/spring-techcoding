package com.thread;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-17 20:40
 */

/**
 * 继承Thread类
 */
public class TestThread1 extends Thread{

    @Override
    public void run() {
        // 方法线程
        for(int i = 0; i < 20; i++) {
            System.out.println("我在数数--> " + i);
        }
    }

    public static void main(String[] args) {
        // main主线程

        TestThread1 testThread1 = new TestThread1();

        // 启动一个分支线程，在JVM中开辟一个新的栈空间，这段代码任务完成之后，瞬间就结束了。
        testThread1.start(); // 不按顺序执行 会有交替，开辟了一个线程执行


        // 不会启动线程，不会分配新的分支栈。（这种方式就是单线程。）
        // testThread1.run(); // 按顺序执行 主线程执行run，执行完，再去执行主线程

        for(int i = 0; i < 20; i++) {
            System.out.println("我在学习多线程--> " + i);
        }
    }
}
