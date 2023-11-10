package com.ueumd.tech.service.userinfo;
import com.ueumd.tech.model.entity.userinfo.Userinfo;
import com.ueumd.tech.model.vo.userinfo.LoginUserinfoVO;
import com.ueumd.tech.model.vo.userinfo.UserinfoLoginResultVO;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-06 10:31
 */
public interface UserinfoService {
    /**
     * 用户注册
     *
     * @param username   用户账户
     * @param password  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String username, String password, String checkPassword);

    /**
     * 用户登录
     *
     * @param username  用户账户
     * @param password 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    UserinfoLoginResultVO userLogin(String username, String password, HttpServletRequest request);

    LoginUserinfoVO getLoginUserVO(Userinfo userinfo);

    LoginUserinfoVO getUserinfo(Long userId);

}
