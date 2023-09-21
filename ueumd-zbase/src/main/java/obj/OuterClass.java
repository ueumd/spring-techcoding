package obj;

public class OuterClass {
    //成员变量
    private String OuterName;
    //成员方法
    public void display(){
        System.out.println("这是外部类方法！");
        System.out.println(OuterName);
    }
    
    //内部类
    public class InnerClass{
        //成员变量
        private String InnerNme;
        //构造方法
        public InnerClass() {
            InnerNme = "Inner Class";
        }
        //成员方法
        public void display(){
            System.out.println("这是内部类方法！");
            System.out.println(InnerNme);
        }
    }
    
    // 主方法
    public void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        outerClass.display();//这是外部类方法！ null

        // 这个类是内部类，已经不是独立的类了，因此不能像外部类一样直接创建！
        //InnerClass innerClass = new InnerClass(); 行不通
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.display();// 这是内部类方法！
    }

}

