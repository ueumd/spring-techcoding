package com.thread;

import static java.lang.Thread.sleep;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-22 20:42
 */
public class JoinTest {
    static int r = 0;

    public static void main(String[] args) throws InterruptedException {
        test();
    }

    private static void test() throws InterruptedException {
        System.out.println("start");
        Thread t =new Thread(() -> {
            System.out.println("start");
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("over");
            r = 10;
        });
        t.start();

        // 插队 打印结果为10
        t.join();

        System.out.println("result: " + r);
        System.out.println("over");
    }
}
