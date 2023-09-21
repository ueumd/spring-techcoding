package com.thread.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-24 11:50
 */
public class UnsafeListSetMap {
    public static void main(String[] args) {
        // list1();

       //  list2();

        // setTest();

        mapTest();
    }


    // java.util.ConcurrentModificationException
    // 并发修改异常
    private static void list2() {


        /**
         *   ConcurrentModificationException
         *   并发下 ArrayList是不安全的
         *   解决方案:
         *   1. List<String> list = new Vector<>();
         *   2. List<String> list = Collections.synchronizedList(new ArrayList<>());
         *   3. List<String> list = new CopyOnWriteArrayList<>();
         *
         *   CopyOnWrite 写入时复制， list读取的时候是固定的，写入（覆盖）
         */
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
    private static void list1() {
        List<String> list = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            list.add(UUID.randomUUID().toString().substring(0, 5));
            System.out.println(list);
        }
    }


    /**
     *  Set<String> list = new HashSet<>();
     *  HashSet底层就是HashMap
     * Exception in thread "15" Exception in thread "14"
     * Exception in thread "9" Exception in thread "12"
     * Exception in thread "6" Exception in thread "7"
     * Exception in thread "11" Exception in thread "21"
     * Exception in thread "25" Exception in thread "26"
     * Exception in thread "13" Exception in thread "29"
     * Exception in thread "28" java.util.ConcurrentModificationException
     *
     * Set<String> list = new CopyOnWriteArraySet<>();
     */
    private static void setTest() {
        Set<String> list = new CopyOnWriteArraySet<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }


    private static void mapTest() {
        /**
         * 面试题
         * Map<String, String> map = new HashMap<>();
         * map是这样用的吗？不是，工作中不用HashMap
         * 默认等价于什么?
         * 1. 加载因子（0.75）new HashMap<>(16, 0.75);
         * 初始化容量
         * Exception in thread "14" Exception in thread "17" java.util.ConcurrentModificationException
         */

        // 不安全的
        // Map<String, String> map = new HashMap<>();

        Map<String, String> map = new ConcurrentHashMap();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
