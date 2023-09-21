package com.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-18 16:39
 *
 * synchronized 内置的Java关键字，Lock是一个Java类
 * synchronized 无法获取锁的状态，Lock可以判断是否获得了锁 lock.trylock()
 * synchronized 会自动释放锁，lock必须要手动释放，否则死锁
 * synchronized 线程1（获得锁，阻塞）,线程2（等待，傻傻的等）；lock锁就不一定会等待下去
 * synchronized 可重入锁，不可以中断的，非公平；lock，可重入锁，可以判断锁，非公平（可以自己设置）
 * synchronized 适合少量的代码同步问题，lock适合大量的同步代码
 *
 */
public class SaleTicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        // 并发 总共50张票 3个线抢占同一个资源
        new Thread(() -> {for (int i = 0; i < 60; i++) ticket.sale();}, "A").start();
        new Thread(() -> {for (int i = 0; i < 60; i++) ticket.sale();}, "B").start();
        new Thread(() -> {for (int i = 0; i < 60; i++) ticket.sale();}, "C").start();

        System.out.println("main------->");
    }
}

class Ticket {

    // 属性
    private int count = 50;

    Lock lock = new ReentrantLock();

    // 方法 synchronized 本质： 队列，锁
    public void sale(){
        lock.lock();
        if (count>0) {
            System.out.println(Thread.currentThread().getName() + " 卖出了 " + count-- + " 票，剩余： " + count);
        }
        // boolean b = lock.tryLock();

        // System.out.println("lock------->" + b);

        lock.unlock();
    }


    // 方法 synchronized 本质： 队列，锁
    public void sale2(){
        if (count>0) {
            System.out.println(Thread.currentThread().getName() + " 卖出了 " + count-- + " 票，剩余： " + count);
        }
    }
}