package com.ueumd.tech.common.annotation;

import java.lang.annotation.*;

/**
 * Description:  JWT验证：资源验证注解
 * Author: hsd
 * Date: 2023-06-08 19:09
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResourceSecurity {
}
