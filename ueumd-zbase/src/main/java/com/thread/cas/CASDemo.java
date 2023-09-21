package com.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-25 21:53
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);

        // 期望、更新
        // public final boolean compareAndSet(int expect, int update)
        // 如果我期望的值达到了，那么就更新，否则，就不更新, CAS 是CPU的并发原语！
        atomicInteger.compareAndSet(2020, 2021);
        System.out.println(atomicInteger.get()); // 2021
    }
}
