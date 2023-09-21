package func.ueumd.tech.io;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 输入流
 * 读数据
 */
public class FileInputStreamTest {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("./temp/test.txt");
        // System.out.println(fis);


        File file = new File("./temp/test.txt");
        FileInputStream fis2= new FileInputStream(file);
        // System.out.println(fis2);

        // 读取数据
        /*int b = fis2.read();
        System.out.println(b);*/


        int b;
        while ((b = fis2.read()) != -1) {
            System.out.println((char) b);
        }

        // 释放资源
        fis2.close();

    }

    @Test
    public void test() throws IOException {
        FileInputStream fis = new FileInputStream("./temp/test.txt");

        byte[] bytes = new byte[5];

        // 获取内容长度
        int len = fis.read(bytes);

        System.out.println(len);

        String s = new String(bytes, 0, len);
        System.out.println(s);

        fis.close();
    }

    @Test
    public void test2() throws IOException {
        FileInputStream fis = null;
        try {
             fis = new FileInputStream("./temp/test.txt");

            byte[] bytes = new byte[1024];
            int len;
            while ((len = fis.read(bytes))!= -1) {
                System.out.println(new String(bytes, 0, len));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            fis.close();
        }
    }
}
