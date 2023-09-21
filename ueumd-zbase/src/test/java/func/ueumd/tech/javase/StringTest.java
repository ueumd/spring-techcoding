package func.ueumd.tech.javase;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class StringTest {
    @Test
    public void test() {
        String str = new String();
        System.out.println(str);
    }

    @Test
    public void test2() {
        byte[] bytes = {97, 98, 99};
        String str = new String(bytes);
        System.out.println(str); //abc
    }

    @Test
    public void test3() {
        byte[] bytes = {97, 98, 99};
        String str = new String(bytes, 0, 1);
        System.out.println(str); // a
    }


    @Test
    public void test4() {
        char[] chars = {'a', 'b', 'c'};
        String str = new String(chars);
        System.out.println(chars); // abc
    }

    @Test
    public void test5() {
        // 字符串转成byte数组
        byte[] bytes = "abcd".getBytes();
        System.out.println(bytes);

        String str = new String(bytes);
        System.out.println(str); // abc
    }

    @Test
    public void testSubstring() {
        String str = "abcd";
        String str2 = str.substring(1);
        System.out.println(str2); // bcd

        // 从0个位置开始截取N个字符串
        System.out.println(str.substring(0, 2)); // ab

        System.out.println(str.substring(0, 3)); // abc

    }


    @Test
    public void testEquals() {
        String s1 = "abcd";
        String s2 = "abcde";

        System.out.println(s1.equals(s2)); // false


        System.out.println("abc".equals("abc")); // true
    }

    @Test
    public void testSplint() {
        String str = "A,B,C";
        String[] list = str.split(",");
        System.out.println(list);

        Arrays.stream(list).forEach(s -> System.out.println(s));

        for (int i =0; i < list.length; i++) {
            System.out.println(list[i]);
        }
    }

    @Test
    public void test6() {
        // 字符串一旦创建，其内容永远是不会改变的

        String str = "abcd";

        // 不会修改str的内容
        String str2 = str.substring(1);
        System.out.println(str2); // bcd


        System.out.println(str); // abcd

        /**
         * https://blog.csdn.net/Prior_SX/article/details/123463430
         * 字符串常量池，垃圾回收不会释放常量的
         * 如String s = "java"这种申明方式。字符串常量池在方法区中（本文是jdk1.7前编辑的，jdk1.7之后字符串常量池移至堆中）
         */

        String s1 = "hello";
        System.out.println(s1);  // hello

        s1 = "123456";
        System.out.println(s1); // 123456

        /**
         * 上面代码是只是输出结果不一样
         * s1 = "hello";  在字符串常量池中创建了 hello
         * s1 = "123456"; 在字符串常量池中创建了 123456, 并将s1的指针指向了新的内容，原内容并没有改变
         */
    }
}
