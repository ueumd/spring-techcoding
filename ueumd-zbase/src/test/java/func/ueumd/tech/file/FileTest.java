package func.ueumd.tech.file;

import java.io.File;
import java.io.IOException;

/**
 * boolean createNewFile)//根据路径创建一个文件，返回值代表创建是否成功
 *
 * boolean mkdir() //根据路径创建一个文件夹，返回值代表创建是否成功
 * boolean mkdirs() //根据路径创建一个文件夹,如果父目录不存在会自动创建父目录
 *
 * boolean exists() //判断文件或者文件夹是否存在
 * boolean isFile() //判断是否是一个文件
 * boolean isDirectory() //判断是否是一个文件夹
 *
 * boolean delete() //删除文件，或者删除空文件夹，返回值代表删除是否成功
 *
 * Tong length() //获取一个文件的大小，对文件夹无意义
 * string getName()//获取文件或文件夹的名字
 *
 * File getParentFile() //获取父目录的File对象
 * string getAbsolutePath()//获取FiTe对象的绝对路径
 */
public class FileTest {
    public static void main(String[] args) throws IOException {
        // 当前项目根目录temp文件夹下
        File file = new File("./temp/test.txt");

        // 是否创建成功
        boolean flag = file.createNewFile();
        System.out.println(flag);


        // listFiles 只能是文件夹，如果是一个文件会有空指针异常
        File dir = new File("./temp");

        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file1: files) {
                System.out.println(file1);
            }
        }

        long f = f(5);
        System.out.println(f);
    }

    public static long f(int n) {
        if (n == 1) {
            return 1;
        }
        return n*f(n-1);
    }
}
