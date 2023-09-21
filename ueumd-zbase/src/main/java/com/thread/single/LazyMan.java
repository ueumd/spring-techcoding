package com.thread.single;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-24 22:55
 */
public class LazyMan {
    private LazyMan(){
        System.out.println(Thread.currentThread().getName() + " ok ");
    }

    private volatile static LazyMan lazyMan;

    /**
     * 多线程并发下 会创建多个实例
     * @return
     */
    public static LazyMan getInstance() {
        // 懒汉式
        if (lazyMan == null) {
            lazyMan = new LazyMan();
        }
        return lazyMan;
    }

    // 双重检测 锁模式 懒汉式单例 DCL懒汉式
    // 双重检测真的没有问题？不是原子性  lazyMan = new LazyMan();
    // 怎么解决？加volatile
    // private volatile static LazyMan lazyMan;
    public static LazyMan getInstance2() {
        if (lazyMan == null) {
           synchronized (LazyMan.class) {
               if (lazyMan == null) {
                   lazyMan = new LazyMan();
                   /**
                    * new LazyMan(); 不是一个原子性操作
                    *  1. 分配内存空间
                    *  2. 执行构造方法
                    *  3. 把这个对象指这个空间
                    */
               }
           }
        }
        return lazyMan;
    }

    public static void main(String[] args) {
        test();
    }

    public static void test() {

        /**
         * 多线程并发下
         */
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LazyMan.getInstance();
                // LazyMan.getInstance2();
            }).start();
        }
    }
}
