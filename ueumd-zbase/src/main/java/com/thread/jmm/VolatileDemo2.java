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
 *
 *
 * Volatile 是可以保持 可见性。不能保证原子性，由于内存屏障，可以保证避免指令重排的现象产生！
 */
public class VolatileDemo2 {

    // 不加 volatile 程序就会死循环！
    // volatile 不保证原子性
    private volatile static int count = 0;

    public static void main(String[] args) {
        //理论上num结果应该为 2 万
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000 ; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount()>2){ // main gc
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " => " + count);
    }

    // 如果不加 lock 和 synchronized ，怎么样保证原子性？原子类
    private synchronized static void add() {
       count++;
    }
}
