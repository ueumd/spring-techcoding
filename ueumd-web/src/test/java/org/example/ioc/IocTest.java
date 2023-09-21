package org.example.ioc;

import org.example.ioc.dao.UserDaoMysqlImpl;
import org.example.ioc.dao.UserDaoOracelImpl;
import org.example.ioc.service.UserService;
import org.example.ioc.service.UserServiceImpl;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-27 23:10
 */

public class IocTest {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        // 用户来控制业务层，dao层他们不需要接触，主动权交给用户来控制
        // ((UserServiceImpl) userService).setUserDao(new UserDaoMysqlImpl());
        ((UserServiceImpl) userService).setUserDao(new UserDaoOracelImpl());

        userService.getUser();
    }
}
