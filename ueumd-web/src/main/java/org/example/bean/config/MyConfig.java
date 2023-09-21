package org.example.bean.config;

import org.example.bean.component.Userinfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-28 13:15
 */

@Configuration
@ComponentScan("org.example.bean")
@Import({MyConfigMySql.class})
public class MyConfig {

    /**
     * 返回一个Userinfo Bean
     * @return
     */
    @Bean
    public Userinfo getUserinfo() {
        return new Userinfo();
    }
}
