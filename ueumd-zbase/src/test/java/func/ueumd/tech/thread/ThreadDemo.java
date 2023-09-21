package func.ueumd.tech.thread;

/**
 * https://www.cnblogs.com/ZhangHao-Study/p/16971276.html
 * @ClassName ThreadDemo
 * @Description TODO 线程创建的第一种方式：继承Thread类
 */
public class ThreadDemo extends Thread {
    @Override
    public void run() {
        //新线程入口点
        for (int i = 0; i < 100; i++) {
            System.out.println("我在玩手机：" + i);
        }
    }

    //主线程
    public static void main(String[] args) {
        //创建线程对象
        ThreadDemo demo = new ThreadDemo();

        //启动线程
        demo.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("我在吃饭：" + i);
        }
        //主线程和多线程并行交替执行
        //总结：线程开启不一定立即执行，由cpu调度执行
    }
}
