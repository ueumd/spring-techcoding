package com.ueumd.tech.model.vo.userinfo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-05 18:50
 */
@Data
public class LoginUserinfoVO implements Serializable {
    /**
     * 用户 id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String username;

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

    private static final long serialVersionUID = 1L;
}
