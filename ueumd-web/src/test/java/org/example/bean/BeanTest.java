package org.example.bean;

import org.example.bean.pojo.User;
import org.example.ioc.service.UserServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-27 23:32
 */
@SpringBootApplication
public class BeanTest {
    public static void main(String[] args) {
        // 获取spring上下文对象
       ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");


        // 现在所有的对象都在Spring中管理了，我们要使用，直接从里面取出来
        User user = (User) context.getBean("user");
        User user2 = (User) context.getBean("u4"); // u4 别名

        System.out.println(user.toString()); // User{name='Bean学习'}
        System.out.println(user + " --> " + user.getClass()); // class org.example.bean.pojo.User

        // 总结：在配置文件加载的时候，容器中管理的对象就已经初始化了！

        System.out.println(user == user2); // true 内存只有一份实例，默认单例


        // 原型模式
        User prototypeUser = (User) context.getBean("prototypeUser");
        User prototypeUser2 = (User) context.getBean("prototypeUser");
        System.out.println(prototypeUser == prototypeUser2); // false

        // 业务层，如果现在想改成 oracle 只需要修改XML配置文件即可
        UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("UserServiceImpl");
        userServiceImpl.getUser(); // Mysql 获取用户数据

    }
}
