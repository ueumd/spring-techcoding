package com.thread;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-18 9:26
 */

/**
 * 模拟网络延时： 放大问题的发生性
 */
public class TestThreadNetTimeout implements Runnable {

    private int ticketNums = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketNums<=0) {
                break;
            }

            // 使用sleep 模拟延时
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName() +"  ---> " + "拿到了第 " + ticketNums-- + " 票");
        }
    }

    public static void main(String[] args) {
        TestThreadNetTimeout person = new TestThreadNetTimeout();

        // 多个线程操作一个对象
        new Thread(person, "小明").start();
        new Thread(person, "小王").start();
        new Thread(person, "黄牛党").start();
    }
}
