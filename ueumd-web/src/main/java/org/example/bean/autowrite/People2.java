package org.example.bean.autowrite;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Description: 使用注解
 * Author: hsd
 * Date: 2023-06-28 10:22
 */
public class People2 {
    @Autowired
    private Cat2 cat;

    @Autowired
    private Dog2 dog;

    @Resource
    private Cat3 cat3;

    private String name;

    public People2() {
    }

    public People2(Dog2 dog, Cat2 cat, String name) {
        this.dog = dog;
        this.cat = cat;
        this.name = name;
    }

    public Cat2 getCat() {
        return cat;
    }

    public Cat3 getCat3() {
        return cat3;
    }

    public Dog2 getDog() {
        return dog;
    }



    @Override
    public String toString() {
        return "People{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }
}
