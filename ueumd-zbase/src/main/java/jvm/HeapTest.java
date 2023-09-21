package jvm;

import java.util.ArrayList;

/**
 * Description: GC
 * Author: hsd
 * Date: 2023-06-19 23:44
 */
public class HeapTest {
    byte[] a = new byte[1024*100]; // 100kb

    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapTest> heapTests = new ArrayList<>();
        while (true) {
            heapTests.add(new HeapTest());
            Thread.sleep(10);
        }
    }
}
