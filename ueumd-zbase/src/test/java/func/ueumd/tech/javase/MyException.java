package func.ueumd.tech.javase;

/**
 * 自定义异常
 *
 * 编译时异常，开发中会有提示信息
 *
 */
public class MyException extends Exception {
    public MyException(String s) {
        super(s);
    }
}

