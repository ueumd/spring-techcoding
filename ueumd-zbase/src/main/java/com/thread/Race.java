package com.thread;

/**
 * Description: 龟免赛跑
 * Author: hsd
 * Date: 2023-06-17 21:09
 */
public class Race implements Runnable {

    // 胜利者
    private static String winner;


    @Override
    public void run() {
        for(int i = 0; i <= 100; i++) {
            // 模拟免子休息
//            if(Thread.currentThread().getName().equals("兔子")) {
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }

            // 判断比赛是否结束
            boolean flag = gameOver(i);
            // 比赛结束
            if (flag) {
                break;
            }

        }
    }

    public boolean gameOver(int steps) {
        if(winner != null) {
            return true;
        }

        System.out.println(Thread.currentThread().getName() + "跑了--> " + steps + " 步" );

        if(steps == 100) {
            winner = Thread.currentThread().getName();
            System.out.println("Winner is " + winner);
            System.out.println("======比赛结束=======");
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();

        new Thread(race, "兔子").start();
        new Thread(race, "乌龟").start();
    }


}
