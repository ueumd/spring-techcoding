package org.example.controller.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-05 15:11
 */
@RestController
@RequestMapping("common")
public class CommonController {

    @GetMapping("start")
    public String getStart(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String date = df.format(System.currentTimeMillis());
        return date;
    }
}
