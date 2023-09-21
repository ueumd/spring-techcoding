package org.example.autowrite;

import org.example.bean.autowrite.People2;
import org.example.bean.component.Userinfo;
import org.example.bean.pojo.People;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-28 10:29
 */

/**
 * 总结：

 都是用来自动装配的，都可以放在*属性字段*上
 @Autowired 通过byType（类型）的方式实现，而且必须要求这个对象存在！
 @Resource 默认通过byName（id）的方式实现，如果找不到名字，则通过byType实现！如果两个都找不到的情况下，就报错！
 执行顺序不同：@Autowired通过byType的方式实现。@Resource默认通过byName的方式实现。

 */
public class AutowriteTest {
    public static void main(String[] args) {

        testAutowrite();
        testAutowriteAnnotation();

        testComponentAnnotation();

    }


    /**
     * 类中使用 get set方式
     */
    public static void testAutowrite  () {
        ApplicationContext context = new ClassPathXmlApplicationContext("autowrite.xml");

        People people = (People) context.getBean("people");

        people.getCat().shout();
        people.getDog().shout();


        People people2 = (People) context.getBean("peopleByType");
        people2.getCat().shout();
        people2.getDog().shout();
    }

    /**
     * 在类中使用注解方式
     */
    public static void testAutowriteAnnotation() {
        /**
         *
         *   <context:annotation-config/>
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("autowrite-annotaion.xml");

        People2 people2 = (People2) context.getBean("people2");
        people2.getCat().shout();
        people2.getDog().shout();

        // Resource
        people2.getCat3().shout();
    }

    public static void testComponentAnnotation() {
        ApplicationContext context = new ClassPathXmlApplicationContext("component-annotaion.xml");
        Userinfo userinfo = (Userinfo) context.getBean("userinfo");
        System.out.println(userinfo.name);
    }
}
