package com.ueumd.tech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync // 开启异步任务
@EnableScheduling // 开启定时任务功能
@MapperScan(basePackages = "com.ueumd.tech.mapper.*")
@SpringBootApplication
public class MainApplication  {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);
        System.out.println(context);
    }
}
