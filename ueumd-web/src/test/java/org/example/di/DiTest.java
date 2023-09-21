package org.example.di;

import org.example.bean.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-28 0:24
 */
public class DiTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("di.xml");
        Student student = (Student)context.getBean("student");
        System.out.println(student + " == " + Student.class);

    }
}
