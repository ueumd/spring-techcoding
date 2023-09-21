package org.example.bean.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-28 11:43
 */

/**
 *  bean注入使用@Componet注解 等价于如下
 *  <bean id="userinfo" class="org.example.bean.component.Userinfo">
 *      <property name="name" value="类上使用@Componet注解开发"></property>
 *  </bean>
 */

@Component
@Scope("singleton")
public class Userinfo {
    @Value("类上使用@Componet注解开发")
    public String name;
}
