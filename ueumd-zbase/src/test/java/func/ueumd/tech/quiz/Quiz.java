package func.ueumd.tech.quiz;

import org.junit.jupiter.api.Test;

public class Quiz {

    @Test
    public void test() {
        Integer a = 127;
        Integer b = 127;
        Integer b1 = new Integer(127);

        System.out.println(a == b); // true
        System.out.println(b == b1); // false 内存地址 b1 是 new 了一个对象

        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d); // false

        /**
         * 如果整型字面量的值在-128 到 127 之间，那么自动装箱时不会 new 新的 Integer 对象，
         * 而是直接引用缓存池中的 Integer 对象，超过范围 c==d 的结果是 false
         */


        String str = "abc";
        String str2 = new String("abc");

        System.out.println(str == str2); // false  虽然值都在是在常量池里取abc，但是两个变量在栈上的地址不一样，比较的是内存地址

        System.out.println(str.equals(str2)); // true   比较的是内容
    }
}
