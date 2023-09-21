package func.ueumd.tech.javase;

import org.junit.jupiter.api.Test;

/**
 * StringBuilder 常用方法
 * append   方法 用于拼接内容
 * reverse  方法可以翻转内容
 * toString 方法 把StringBuilder转换为String类型
 */
public class StringBuilderTest {

    @Test
    public void test() {
        String s = "";
        for (int i =1 ; i<=10; i++) {
            s += i;
        }

        // 最终拼接到10，前面的拼接浪费空间
        System.out.println(s); // 12345678910
    }

    @Test
    public void testStringBuilder() {
        StringBuilder sb = new StringBuilder("Hello");
        System.out.println(sb);

        StringBuilder sb2 = new StringBuilder();
        sb2.append(1);
        sb2.append(3);

        System.out.println(sb2);

        System.out.println(sb2.reverse());
    }
}
