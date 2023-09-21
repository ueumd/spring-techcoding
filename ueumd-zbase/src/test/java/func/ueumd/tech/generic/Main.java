package func.ueumd.tech.generic;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Box<String> box = new Box<>();
        box.put("java");
        System.out.println(box.s);


        SubBox subBox = new SubBox();
        subBox.put("go");
        System.out.println(subBox.str);

        // 传入数字 返回 数字类型
        System.out.println(SubBox.test(1));

        // 传入字符串 返回 String类型
        System.out.println(SubBox.test("React"));

        System.out.println(SubBox.test2("Next.js"));

        String[] strings = {"hello", "B"};

        String[] list = SubBox.getArray(strings);

        Arrays.stream(list).forEach(it -> System.out.println(it));

        ArrayList<Person> list1 = new ArrayList<>();
        list1.add(new Person("Tom", 1));

        SubBox.test3(list1);

        ArrayList<Student> list2 = new ArrayList<>();
        list2.add(new Student("Jack", 1));
        SubBox.test3(list2);


        SubBox.test4(list1);
        SubBox.test4(list2);


        ArrayList<String> list3 = new ArrayList<>();
        list3.add("hello");
        // SubBox.test4(list3); 类型不符
    }
}
