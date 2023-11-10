package com.ueumd.tech.model.dto.userinfo;

import lombok.Data;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-05 18:42
 */
@Data
public class UserLoginRequestDTO {
    private static final long serialVersionUID = 3191241716373120793L;

    private String username;

    private String password;
}
