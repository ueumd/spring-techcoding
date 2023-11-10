package com.ueumd.tech.model.vo.userinfo;

import com.ueumd.tech.common.entity.TokenPackage;
import lombok.Data;

/**
 * Description: 登录VO
 * Author: hsd
 * Date: 2023-06-08 21:24
 */
@Data
public class UserinfoLoginResultVO {
    private String token;
    private TokenPackage user;

    public UserinfoLoginResultVO(String token, TokenPackage user) {
        this.token = token;
        this.user = user;
    }
}
