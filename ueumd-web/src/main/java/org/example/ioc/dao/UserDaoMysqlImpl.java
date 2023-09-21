package org.example.ioc.dao;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-27 23:05
 */
public class UserDaoMysqlImpl implements UserDao {
    @Override
    public void getUser() {
        System.out.println("Mysql 获取用户数据");
    }
}
