package com.thread.jmm;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-24 22:28
 */

import java.util.concurrent.TimeUnit;

/**
 * 什么是JMM
 * JMM ： Java内存模型，不存在的东西，概念！约定！
 *
 * 关于JMM的一些同步的约定：
 * 1、线程 解锁前，必须把 共享变量 立刻刷回 主存。
 * 2、线程 加锁前，必须读取 主存 中的最新值到 工作内存 中！
 * 3、加锁和解锁是同一把锁
 * 线程 工作内存 、主内存
 *
 * 请你谈谈你对 Volatile 的理解
 * Volatile 是 Java 虚拟机提供轻量级的同步机制
 * 1、保证可见性
 * 2、不保证原子性
 * 3、禁止指令重排
 */
public class VolatileDemo {

    // 不加 volatile 程序就会死循环！
    // 加 volatile 可以保证可见性
    private volatile static int count = 0;

    public static void main(String[] args) {
        test();
    }

    private static void test() {

        // 线程1 知道主内存的值已经被修改过了
        new Thread(() -> {
            while (count == 0) {
                // T1 count: 0
                System.out.println(Thread.currentThread().getName() + " count: " + count);
            }
        }, "T1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 线程2 修改时 刷回主内存 但是线程 T1工作内存还是0
        // 问题： T1线程 不知道主内存的值已经被修改过了
        count = 1;
        System.out.println("count: " + count); // 1
    }
}
