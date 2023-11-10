package com.ueumd.tech.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

/**
 * Description: 拦截器配置
 * Author: hsd
 * Date: 2023-06-08 19:18
 */
@EnableConfigurationProperties(NoAuthUrlProperties.class)
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private NoAuthUrlProperties noAuthUrlProperties;

    public static int limitTimes;

    public static int intervalTime;

    @Value("${system.config.limit-times}")
    public static void setLimitTimes(Integer limitTimes) {
        WebConfig.limitTimes = limitTimes;
    }

    @Value("${system.config.interval-times}")
    public static void setIntervalTime(Integer intervalTime) {
        WebConfig.intervalTime = intervalTime;
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(
                new JwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(new ArrayList<>(noAuthUrlProperties.getSkipUrls())
        );
    }
}
