package com.thread.juc;

import java.util.concurrent.*;

/**
 * Description: JVM 并发工具类
 * Author: hsd
 * Date: 2023-06-24 13:48
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
//        testCountDownLatch();
//        testCyclicBarrier();

        testSemaphore();
    }

    /**
     * 减法计数器
     * CountDownLatch 能够使一个线程在等待另外一些线程完成各自工作之后，再继续执行
     */
    private static void testCountDownLatch() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " go out");
                countDownLatch.countDown(); // 数量-1
            }, String.valueOf(i)).start();
        }

        // 等待计数器归零，然后再向下执行
        // 等上面6个线程再往下执行
        countDownLatch.await();

        System.out.println("Close Door");
    }


    /**
     * 7 龙珠
     * 加法计数器
     * CyclicBarrier 能够使多个线程等待一个条件达成之后，再继续执行
     */
    private static void testCyclicBarrier() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()-> {
            System.out.println("召唤神龙成功");
        });

        for (int i = 1; i <= 7; i++) {
            int temp = i;
            new Thread(()-> {
                System.out.println(Thread.currentThread().getName() + " 收集 " + temp + "个龙珠");

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }

            }, String.valueOf(i)).start();
        }
    }


    /**
     * 限流
     */
    private static void testSemaphore() {

        // 线程数量 停车位
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                // acquire() 得到
                // release() 释放
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+ " 抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + " 离开车位");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
