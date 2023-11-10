package com.ueumd.tech.config;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * Description: TODO
 * Author: hsd
 * @slogan: 天下风云出我辈，一入代码岁月催
 * Date: 2023-06-08 19:26
 */
@Data
@ConfigurationProperties(prefix = "user.auth")
public class NoAuthUrlProperties {
    @JSONField(name = "skip-urls")
    private LinkedHashSet<String> skipUrls;

    @JSONField(name = "cross-domains")
    private List<String> crossDomains;
}
