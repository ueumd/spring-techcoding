package func.ueumd.tech.javase;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 异常
 */

public class ThrowableTest {

    /**
     * 运行时异常
     * NullPointerException
     */
    @Test
    public void test() {
        String s = null;
        System.out.println(s.length()); // java.lang.NullPointerException 空指针

        // 上面异常了，但是没有处理，后面的代码不会执行
        System.out.println("hello");
    }

    /**
     * 方法正常执行
     */

    @Test
    public void test3() {
        String s = null;
        try {
            System.out.println(s.length());
        } catch (NullPointerException nullPointerException) {
            System.out.println(nullPointerException);
        } finally {
            System.out.println("上面的方法异常了");
        }

        // 会执行
        System.out.println("hello");
    }

    /**
     * 编译异常
     * FileNotFoundException
     *
     * @throws FileNotFoundException
     */
    @Test
    public void test2() throws FileNotFoundException, ParseException {
        FileInputStream fileInputStream = new FileInputStream("aa.txt");
        System.out.println(fileInputStream);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("www");

        simpleDateFormat.parse("ss");
    }


    /**
     * 多个catch 处理方式一
     */

    @Test
    public void testCatch1() {
        try {
            FileInputStream fileInputStream = new FileInputStream("aa.txt");
            System.out.println(fileInputStream);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("www");

            simpleDateFormat.parse("ss");
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        } finally {
            System.out.println("上面的方法异常了");
        }

        // 会执行
        System.out.println("hello");
    }


    /**
     * 多个catch 处理方式二
     */
    @Test
    public void testCatch2() {
        try {
            FileInputStream fileInputStream = new FileInputStream("aa.txt");
            System.out.println(fileInputStream);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("www");

            simpleDateFormat.parse("ss");
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            System.out.println("上面的方法异常了");
        }

        // 会执行
        System.out.println("hello");
    }


    /**
     * 自定义异常
     */

    @Test
    public void testException() throws MyException {
        throw new MyException("出现了异常");
    }

    @Test void runTestException () throws MyException {
        // testException(); // func.ueumd.tech.javase.MyException: 出现了异常

        try {
            testException();
        } catch (MyException myException) {
            myException.printStackTrace(); // func.ueumd.tech.javase.MyException: 出现了异常
        } finally {
            System.out.println("上面的方法异常了");
        }

        // 会执行
        System.out.println("hello");
    }
}
