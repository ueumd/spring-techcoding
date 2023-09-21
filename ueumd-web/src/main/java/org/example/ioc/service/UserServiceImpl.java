package org.example.ioc.service;

import org.example.ioc.dao.UserDao;
import org.example.ioc.dao.UserDaoImpl;
import org.example.ioc.dao.UserDaoMysqlImpl;
import org.example.ioc.dao.UserDaoOracelImpl;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-27 23:06
 */
public class UserServiceImpl implements UserService {


    /**
     * 如果扩展了一个Mysql 或 oracle 那服务层又要改动
     */
//    private UserDao userDao = new UserDaoImpl();
//    private UserDao userDao2 = new UserDaoMysqlImpl();
//    private UserDao userDao1 = new UserDaoOracelImpl();

    private UserDao userDao;

    // IOC反转 把 注入交给用户
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
