package com.thread.juc;

import java.util.concurrent.*;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-24 20:55
 */
public class ThreadPoolExecutorsDemo {
    public static void main(String[] args) {


        test2();
    }

    private static void test() {
        // Executors 工具类、3大方法
        // ExecutorService threadPool = Executors.newSingleThreadExecutor(); // 单个线程
        // ExecutorService threadPool = Executors.newFixedThreadPool(5);     // 创建一个固定的线程池的大小
        // ExecutorService threadPool = Executors.newCachedThreadPool();     // 可伸缩的，遇强则强，遇弱则弱
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }

    /**
     * new ThreadPoolExecutor.AbortPolicy() // 银行满了，还有人进来，不处理这个人的，抛出异 常
     * new ThreadPoolExecutor.CallerRunsPolicy() // 哪来的去哪里！
     * new ThreadPoolExecutor.DiscardPolicy() //队列满了，丢掉任务，不会抛出异常！
     * new ThreadPoolExecutor.DiscardOldestPolicy() //队列满了，尝试去和最早的竞争，也不会抛出异常！
     */

    private static void test1() {
        // ExecutorService threadPool = Executors.newSingleThreadExecutor(); // 单个线程
        // ExecutorService threadPool = Executors.newFixedThreadPool(5);     // 创建一个固定的线程池的大小
        // ExecutorService threadPool = Executors.newCachedThreadPool();     // 可伸缩的，遇强则强，遇弱则弱

        /**
         * 自定义线程池 *****
         */
        // 底层 7大参数
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 程序结束，关闭线程池
            threadPool.shutdown();
        }

    }


    /**
     * 池的最大的大小如何去设置！
     * 了解：IO密集型，CPU密集型：（调优）
     */
    private static void test2() {
        /**
         * 自定义线程池 *****
         */

        // 最大线程到底该如何定义
        // 1、CPU 密集型，几核，就是几，可以保持CPu的效率最高！
        // 2、IO 密集型 > 判断你程序中十分耗IO的线程，
        // 程序 15个大型任务 io十分占用资源！


        // 获取CPU的核数
        int cpu = Runtime.getRuntime().availableProcessors();
        System.out.println(cpu);

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                cpu,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        try {
            for (int i = 0; i < 300; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 程序结束，关闭线程池
            threadPool.shutdown();
        }

    }
}


