package com.thread;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-18 10:46
 */
public class TestThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
           for (int i = 0; i< 5; i++) {
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println("print i: " + i);
           }
        });

        // 观察状态
        Thread.State state = thread.getState();
        System.out.println("当前state: " + state); //NEW

        // 观察启动后
        thread.start();
        state = thread.getState();
        System.out.println("启动后state: " + state); // Run

        while (state != Thread.State.TERMINATED) {
            // 线程不终止，就一直输出
            Thread.sleep(100);
            state = thread.getState(); // 更新线程状态
            System.out.println(state);
        }

        thread.start();
    }
}
