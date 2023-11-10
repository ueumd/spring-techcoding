package com.ueumd.tech.common.entity;

import com.ueumd.tech.common.TokenPackageType;

import java.util.Date;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-06 23:31
 */
public class User extends TokenPackage {
    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户头像
     */
    private String userAvatar;
    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @Override
    public void setIndependType() {
        setType(TokenPackageType.USER.getName());
    }
}
