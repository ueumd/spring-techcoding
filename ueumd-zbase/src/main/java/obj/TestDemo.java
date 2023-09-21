package obj;


public class TestDemo{
    
    public static void main(String[] args)throws Exception{
        Runtime runtime=Runtime.getRuntime();
        Process pro=runtime.exec("mspaint.exe");//调用本机可执行程序
        Thread.sleep(5000);
        pro.destroy();//销毁进程
    }   
}