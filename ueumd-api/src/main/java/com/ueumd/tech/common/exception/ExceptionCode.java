package com.ueumd.tech.common.exception;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-05 14:51
 */
public enum ExceptionCode {
    SYSTEM_ERROR(700, "System error"),

    FEIGN_ERROR(701, "Runtime error"),

    BUSINESS_ERROR(702, "Business error"),

    SUCCESS(0, "ok"),
    PARAMS_ERROR(40000, "请求参数错误"),
    NOT_LOGING_ERROR(40100, "未登录"),
    NO_AUTH_ERROR(40101, "无权限"),
    NOT_FOUND_ERROR(40400, "请求数据不存在"),
    FORBIDDEN_ERROR(40030, "禁止访问"),
    // SYSTEM_ERROR(50000, "系统内部异常"),
    OPERATION(50001, "操作失败");

    /**
     * 状态码
     */
    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
