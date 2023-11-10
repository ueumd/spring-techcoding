package com.ueumd.tech.common.annotation;

import java.lang.annotation.*;

/**
 * Description: JWT验证忽略注解
 * Author: hsd
 * Date: 2023-06-06 23:40
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JwtIgnore {
}
