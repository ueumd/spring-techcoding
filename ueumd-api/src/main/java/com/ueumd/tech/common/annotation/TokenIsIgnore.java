package com.ueumd.tech.common.annotation;

import java.lang.annotation.*;

/**
 * Description:  token 验证是否忽略注解(有token校验，无token不校验)
 * Author: hsd
 * Date: 2023-06-06 23:41
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenIsIgnore {
}
