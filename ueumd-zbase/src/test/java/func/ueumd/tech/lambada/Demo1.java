package func.ueumd.tech.lambada;

public class Demo1 {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("你知道吗 我比你想象的 更想在你身边");
        }).start();
    }
}
