package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出
 * Description: java.lang.OutOfMemoryError: Java heap space
 * Author: hsd
 * Date: 2023-06-19 21:45
 *
 * 堆空间设置 -Xmm8m
 */
public class HeapSpace {
    public static void main(String[] args) {
        int i = 0;

        try {
            List<String> list = new ArrayList<>();
            String a = "hello";
            while (true) {
                list.add(a); // hello, hellohello, hellohellohellohello, ...
                a = a + a;
                i ++;
            }
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(i);
        }
    }
}
