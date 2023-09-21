package func.ueumd.tech.io;

import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * https://blog.csdn.net/qq_52519008/article/details/127135476
 */

public class FileOutputStreamTest {

    @Test
    public void test() throws IOException {
        FileOutputStream fos = new FileOutputStream("./temp/out.txt");

        fos.write('a');

        fos.close();
    }

    @Test
    public void test2() throws IOException {
        FileOutputStream fos = new FileOutputStream("./temp/out2.txt");

        byte[] bytes ="abc".getBytes();


        fos.write(bytes, 0, 2);

        fos.close();
    }


    @Test
    public void test3() throws IOException {
        // 传入 true 续写操作
        FileOutputStream fos = new FileOutputStream("./temp/out2.txt", true);

        byte[] bytes ="abc".getBytes();


        fos.write(bytes, 0, 2);

        fos.close();
    }
}
