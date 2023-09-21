package com.thread;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-17 20:48
 */

/**
 * 实现Runnable类 只有一个run方法
 *
 * package java.lang;
 *
 * @FunctionalInterface
 * public interface Runnable {
 *     void run();
 * }
 *
 */
public class TestThread2 implements Runnable {
    @Override
    public void run() {
        // 方法线程
        for(int i = 0; i < 20; i++) {
            System.out.println("2我在数数--> " + i);
        }
    }

    public static void main(String[] args) {
        // main主线程

        TestThread2 testThread2 = new TestThread2();

        // new Thread(testThread2).start();
        new Thread(testThread2).run();

        for(int i = 0; i < 20; i++) {
            System.out.println("2我在学习多线程--> " + i);
        }
    }
}