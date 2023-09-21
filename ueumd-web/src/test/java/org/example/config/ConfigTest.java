package org.example.config;

import org.example.bean.component.Userinfo;
import org.example.bean.config.MyConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-28 13:17
 */
public class ConfigTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Userinfo userinfo = (Userinfo) context.getBean("userinfo");
        System.out.println(userinfo);
        /**
         Exception in thread "main"
         org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'userinfo' available

         在MyConfig中没有扫描到 userinfo
         添加扫描注解
         @ComponentScan("org.example.bean")
         */
    }
}
