package org.example.ioc.dao;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-27 23:04
 */
public class UserDaoImpl implements UserDao{
    @Override
    public void getUser() {
        System.out.println("获取用户数据");
    }
}
