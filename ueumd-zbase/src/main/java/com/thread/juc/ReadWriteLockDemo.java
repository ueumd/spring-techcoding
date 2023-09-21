package com.thread.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-24 19:30
 *
 * 独占锁 （写锁）一次只能一个线程占有
 * 共享锁 （读锁）多个线程可以同时占有
 *
 * ReadWriteLoc
 * 读-读 共存
 * 读-写 不能共存
 * 写-写 不能共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache2 = new MyCache();

        MyCacheLock myCache = new MyCacheLock();
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() ->{
                myCache.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() ->{
                myCache.get(temp + "");
            }, String.valueOf(i)).start();
        }
    }
}

class MyCacheLock {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    // 存 写
    public void put(String key, Object value) {
        lock.writeLock().lock();
        // 写的时候只能有一个操作 不会被插队
        System.out.println(Thread.currentThread().getName() + " 写入 " + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + " 写入OK ");
        lock.writeLock().unlock();
    }

    // 读 取
    public void get(String key) {
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + " 读取 " + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + " 读取OK ");
        lock.readLock().unlock();
    }
}


class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    // 存 写
    public void put(String key, Object value) {
        // 写的时候只能有一个操作
        System.out.println(Thread.currentThread().getName() + " 写入 " + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + " 写入OK ");
    }

    // 读 取
    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + " 读取 " + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + " 读取OK ");
    }
}
