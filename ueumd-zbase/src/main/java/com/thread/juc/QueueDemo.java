package com.thread.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-24 20:02
 */
public class QueueDemo {
    public static void main(String[] args) {
        // blockingQueueTest();

       //  blockingQueueTest2();

        synchronousQueueTest();
    }

    /**
     * 先进先出 add remove
     */
    private static void blockingQueueTest() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        // 满了？
        // System.out.println(blockingQueue.add("c"));

        System.out.println("=================");

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        // 再移出一个？？？异常
        // System.out.println(blockingQueue.remove());

        /**
         * true
         * true
         * true
         * =================
         * a
         * b
         * c
         */
    }

    /**
     * 推荐
     * offer
     * poll
     */
    private static void blockingQueueTest2() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        // false 不抛出异常
        System.out.println(blockingQueue.offer("d"));

        // a 检测队首元素
        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        // null 不抛出异常
        System.out.println(blockingQueue.poll());
    }


    /**
     * 同步队列 不存元素 put一个元素，必须从里面先take取出来，否则不能在put进去
     */
    private static void synchronousQueueTest() {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                String name = Thread.currentThread().getName();

                System.out.println(name + " put 1");
                blockingQueue.put("1");


                System.out.println(name + " put 2");
                blockingQueue.put("2");


                System.out.println(name + " put 3");
                blockingQueue.put("3");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "T1").start();


        new Thread(() -> {
            try {

                String name = Thread.currentThread().getName();

                TimeUnit.SECONDS.sleep(3);
                System.out.println(name + " => " + blockingQueue.take());

                TimeUnit.SECONDS.sleep(3);
                System.out.println(name + " => " + blockingQueue.take());

                TimeUnit.SECONDS.sleep(3);
                System.out.println(name + " => " + blockingQueue.take());


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "T2").start();

        /**
         * T1 put 1
         * T2 => 1
         * T1 put 2
         * T2 => 2
         * T1 put 3
         * T2 => 3
         */

    }
}
