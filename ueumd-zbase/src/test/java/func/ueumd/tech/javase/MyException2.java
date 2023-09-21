package func.ueumd.tech.javase;

/**
 * 运行时异常
 * 只有在运行时才会有报错
 */
public class MyException2 extends RuntimeException {
    public MyException2(String s) {
        super(s);
    }
}
