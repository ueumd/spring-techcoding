package com.thread.juc;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Description: 四大函数式接口
 * Author: hsd
 * Date: 2023-06-24 21:36
 * Consumer
 * Function
 * Predicate
 * Supplier
 */
public class FunctionAndStreamDemo {
    public static void main(String[] args) {



    }

    /**
     * 四大函数式接口
     */
    private static void func() {
        // 1. Function 函数型接口, 有一个输入参数，有一个输出
        Function function = (str) -> { return str;};
        System.out.println(function.apply("hello function"));


        // 2. 断定型接口，有一个输入参数，返回只能是布尔值
        Predicate<String> stringPredicate = new Predicate<String>() {
            @Override
            public boolean test(String str) {
                return  str.isEmpty();
            }
        };

        System.out.println(stringPredicate.test("hello"));


        // 3. Consumer 消费型接口: 只有输入，没有返回值
        Consumer<String> consumer = (str) -> {
            System.out.println(str);
        };

        consumer.accept("hello");


        // 4. Supplier 供给型接口 没有参数，只有返回值
        Supplier supplier = ()->{ return 1024; };
        System.out.println(supplier.get());
    }
}
