package org.example.bean.pojo;

public class User {
    private String name;
    public User() {
        System.out.println("User 空参构造方法执行了");
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
