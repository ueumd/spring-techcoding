package com.ueumd.tech.model.entity.userinfo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-06 10:43
 */
@TableName(value = "user")
@Data
public class Userinfo {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

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

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
