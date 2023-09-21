package com.thread;

/**
 * Description: 生产者/消费者模式
 * Author: hsd
 * Date: 2023-06-18 15:14
 */
public class TestPC {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();
        new Productor(container).start();
        new Consumer(container).start();
    }
}

// 生产者
class Productor extends Thread {

    SynContainer container;

    public Productor(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            container.push(new Chicken(i));
            System.out.println("生产了第 " + i + " 只鸡");
        }
    }
}

// 消费者
class Consumer extends Thread{

    SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println("消费了第--> " + container.pop().id + " 只鸡");
        }
    }
}

// 产品
class Chicken{
    int id;
    public Chicken(int id) {
        this.id = id;
    }
}

// 缓冲区
class SynContainer {
    Chicken[] chickens = new Chicken[10];

    int count = 0;

    public synchronized void push(Chicken chicken) {
        if (count == chickens.length) {
            // 通知消费者消费，生产等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 生产 缓存
        chickens[count] = chicken;
        count++;

        // 可以调通知消费者消费了
        this.notifyAll();
    }


    // 消费者消费产品
    public synchronized Chicken pop() {
        if (count == 0) {
            // 等待生产者生产
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        count--;
        Chicken chicken = chickens[count];

        // 吃完了通知生产者生产
        this.notifyAll();
        return chicken;
    }
}