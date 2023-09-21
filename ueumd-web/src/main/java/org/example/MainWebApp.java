package org.example;

import org.example.bean.component.Userinfo;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Field;
import java.util.Map;

@SpringBootApplication
public class MainWebApp {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        System.out.println("Hello world!");

        // Ctrl + alt + u 查看
        ConfigurableApplicationContext context = SpringApplication.run(MainWebApp.class, args);

        System.out.println(context);

        /*spring boot做了xml加载处理*/
        Userinfo userinfo = (Userinfo) context.getBean("userinfo");
        System.out.println(userinfo.name);

        // Ctrl + alt + b
        // ctrl + alt + 鼠标左键

        // context.getBean("aaa");


        // ctrl + n 查找全类 BeanFactory
        // ctrl + F12 查看所有方法

        /**
         * 1. 到底什么是BeanFactory
         *  它是ApplicationContext 的父母接口
         *  它是Spring的核心容器，主要是的ApplicationContext 实现都组合了它的功能
         *
         *  2. BeanFactory 能干点啥？
         *  - 表面上只有 getBean
         *  - 实际上控制反转，基本的依赖注入、直至Bean的生命周期的各种功能，都由它的实现类提供
         */


        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");

        singletonObjects.setAccessible(true);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        Map<String, Object> map =(Map<String, Object>) singletonObjects.get(beanFactory);

//        map.forEach((k, v) -> {
//            System.out.println(k + " =======>  " + v);
//        });

        // 只查手动注入的两个
        map.entrySet().stream().filter(e -> e.getKey().startsWith("component")).forEach(item -> {
            System.out.println(item.getKey() + " ---> " + item.getValue());
        });
    }
}