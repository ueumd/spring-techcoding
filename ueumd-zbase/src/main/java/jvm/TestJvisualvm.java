package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-19 22:03
 */
public class TestJvisualvm {
    public static void main(String[] args) throws InterruptedException {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            // 程序结束依然会占用大量内存
            list.add(new Student());
        }

        Thread.sleep(100000000L);
    }
}

class Student {
    private byte[] big = new byte[1024*1024];
}
