package com.thread;


/**
 * https://blog.csdn.net/qq_43617838/article/details/109496456
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-18 10:04
 */

class Confucian extends Thread {

    //   提前结束线程操作
    public boolean quitflag = false;

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            // 提前结束线程操作
            if (quitflag) break;

            System.out.println("VIP 线程来了 " + i);

//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}


public class TestThreadJoin {

    public static void main(String[] args) throws InterruptedException {

        // 创建并启动线程
        Confucian c = new Confucian();
        // c.start();

//        Thread thread = new Thread(c);
//        thread.start();

        // 主线程
        for(int i = 0; i< 100; i++) {
            if (i == 50) {
                c.start();
                // c.join(); // 插队, 阻塞主线程
            }
            System.out.println("main: " + i);
        }
    }
}
