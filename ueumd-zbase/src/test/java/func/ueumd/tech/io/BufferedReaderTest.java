package func.ueumd.tech.io;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderTest {
    @Test
    public void test() throws IOException {
        FileReader fileReader = new FileReader("./temp/test.txt");

        // 缓冲流
        BufferedReader br = new BufferedReader(fileReader);

        // 读取
        char[] chars = new char[1024];
        int len;

        while ((len = br.read(chars)) != -1) {
            System.out.println(chars);
        }

        br.close();
    }
}
