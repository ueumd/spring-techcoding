package func.ueumd.tech.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * 泛型
 * @param <T>
 */
class Box<T> {
    public T s;

    public void put(T t) {
        this.s = t;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person {
    public String name;
    public Integer age;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Student extends Person {
    public String name;
    public Integer age;
}


/**
 *
 */
public class SubBox extends Box<String>{
    public String str;


    public void put(String s) {
        str = s;
    }


    public <T> T[] toArray(T[] a) {
        return a;
    }


    /**
     * 泛型方法 自动推导
     * @param t
     * @return
     * @param <T>
     */
    public static  <T> T test(T t) {
        return t;
    }

    public static <T> T[] getArray(T[] arr) {
        return arr;
    }

    public static String test2(String str) {
        System.out.println(str);
        return str;
    }


    /**
     * 泛型上限
     * 传入的类型要么是Person 要么是Person的子类
     * 这样可以使用Person特有的方法
     */
    public static void test3(List<? extends Person> list) {
        Person person = list.get(0);
        System.out.println(person.getName());
    }

    /**
     * 泛型下限 必须是 Student 或 父类
     * @param list
     */
    public static void test4(List<? super Student> list) {
        System.out.println(list);
    }
}
