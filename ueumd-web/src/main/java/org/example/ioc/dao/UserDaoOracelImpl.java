package org.example.ioc.dao;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-27 23:05
 */
public class UserDaoOracelImpl implements UserDao {
    @Override
    public void getUser() {
        System.out.println("Oracle 获取用户数据");
    }
}
