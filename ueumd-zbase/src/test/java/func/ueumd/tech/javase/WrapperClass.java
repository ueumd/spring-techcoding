package func.ueumd.tech.javase;

import org.junit.jupiter.api.Test;

/**
 *  包装类
 *  - https://blog.csdn.net/weixin_37766087/article/details/93405801
 *
 *  - https://www.dgrt.cn/news/show-936273.html?action=onClick
 */
public class WrapperClass {

    @Test
    public void testInteger() {
        Integer integer1= Integer.valueOf("10");

        // String  -> Integer
        System.out.println(integer1); // 10

        int i = Integer.parseInt("20");

        // String -> int
        System.out.println(i);
    }

    /**
     * 自动装箱  自动装箱其实是通过包装类的静态方法valueOf来实现的转换。
     * 自动拆箱
     *
     * 包装类中的自动装箱拆箱机制
     * Integer  num1 = 1;		//自动装箱
     * int num2 = num1;		//自动拆箱
     *
     */
    @Test
    public void test() {
        // 自动装箱 自动装箱即自动将基本数据类型转换成包装类型
        Integer i1 = new Integer(8);
        Integer i2 = Integer.valueOf(8);
        Integer i3 = 8;

        // 自动拆箱
        int i4 = i3;
        int i5 = i3.intValue();
    }
}
