package jvm;

/**
栈内存溢出
 linux mac 默认1024kb
 windows 根据虚拟来内存来

 idea 分配栈内存 VM Options -Xss256k 设置后count会小很多
 */
public class TestStackOverflowError {

    private static int count;

    public static void main(String[] args) {
        try {
            run();
        } catch (Throwable e) {
            // java.lang.StackOverflowError
            e.printStackTrace();
            System.out.println(count);
        }
    }

    public static void run() {
        count ++;
        run();
    }
}
