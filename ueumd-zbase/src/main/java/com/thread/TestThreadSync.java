package com.thread;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-18 11:15
 */

/**
 * 不安全的买票
 * 线程不安全，有负数
 * 解决方式：线程同步
 */
class BuyTicket implements Runnable {

    private int ticketNums = 10;
    boolean flag = true; // 外部停止方式

    @Override
    public void run() {
        while (flag) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 加上 synchronized 同步方法
    private synchronized void buy() throws InterruptedException {
        if(ticketNums <=0) {
            flag = false;
            return;
        }

        Thread.sleep(100);

        System.out.println(Thread.currentThread().getName() + " 拿到了 " + ticketNums--);
    }
}


public class TestThreadSync {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();

        new Thread(buyTicket, "苦逼的我").start();
        new Thread(buyTicket, "牛逼的你们").start();
        new Thread(buyTicket, "可恶的黄牛党").start();
    }
}
