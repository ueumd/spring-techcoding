package com.ueumd.tech.controller.common;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.ueumd.tech.common.annotation.JwtIgnore;
import com.ueumd.tech.common.vo.ResponseDTO;
import com.ueumd.tech.service.async.AsycQueryUserinfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-05 15:11
 */
@Slf4j
@RestController
@RequestMapping("common")
public class CommonController {

    @Autowired
    private AsycQueryUserinfoService asycQueryUserinfoService;
    @JwtIgnore
    @GetMapping("getSystemDate")
    public ResponseDTO<String> getSystemDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String date = df.format(System.currentTimeMillis());
        log.info("===========" + date);
        log.debug("===========" + date);
        log.error("===========" + date);
        return new ResponseDTO<>(date);
    }

    @GetMapping("start")
    public ResponseDTO<String> getStart(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String date = df.format(System.currentTimeMillis());
        return new ResponseDTO<>(date);
    }

    @JwtIgnore
    @GetMapping("/druid/stat")
    public Object druidStat(){
        // DruidStatManagerFacade#getDataSourceStatDataList 该方法可以获取所有数据源的监控数据，除此之外 DruidStatManagerFacade 还提供了一些其他方法，你可以按需选择使用。
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }

    /**
     * 异步
     * @return
     * @throws InterruptedException
     */
    @JwtIgnore
    @GetMapping("asyncDemo")
    public ResponseDTO<String> asyncDemo() throws InterruptedException {
        asycQueryUserinfoService.selectUserinfo();
        System.out.println("========");
        return new ResponseDTO<>("ok");
    }

    @JwtIgnore
    @GetMapping("version")
    public ResponseDTO<?> getVersion() {
        String jdkVersion = System.getProperty("java.version");
        String springVersion = SpringVersion.getVersion();
        String springBootVersion = SpringBootVersion.getVersion();

        Map<String, String> map = new HashMap<>();
        map.put("jdkVersion", jdkVersion);
        map.put("spring", springVersion);
        map.put("springBootVersion", springBootVersion);

        return new ResponseDTO<>(map);
    }
}
