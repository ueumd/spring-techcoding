package com.ueumd.tech.common;

/**
 * Description: token包装类枚举类型
 * Author: hsd
 * Date: 2023-06-06 23:25
 */
public enum TokenPackageType {
    USER("user", "管理用户");


    //代号英文名称
    private String name;
    //代号中文名称
    private String chineseName;

    TokenPackageType(String name, String chineseName) {
        this.name = name;
        this.chineseName = chineseName;
    }

    //直接获取英文
    public String getName() {
        return name;
    }

    //直接获取中文
    public String getChineseName() {
        return chineseName;
    }
}
