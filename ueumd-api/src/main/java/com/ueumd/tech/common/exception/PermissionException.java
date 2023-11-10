package com.ueumd.tech.common.exception;

/**
 * Description: token异常
 * Author: hsd
 * Date: 2023-06-08 15:31
 */
public class PermissionException extends BusinessException {
    private static final long serialVersionUID = 4286388973414017306L;
    public static final PermissionException PERMISSION_TOKEN_INVALID = new PermissionException(604, "token解析异常", new Object[0]);
    public static final PermissionException PERMISSION_TOKEN_EXPIRE = new PermissionException(603, "token过期", new Object[0]);
    public static final PermissionException PERMISSION_USER_NOT_LOGIN = new PermissionException(602, "用户未登录", new Object[0]);
    public static final PermissionException PERMISSION_USER_LOGIN_INVALID = new PermissionException(600, "该账号已在其他设备登录，请重新登录？", new Object[0]);
    protected String msg;
    protected int code;

    public PermissionException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }

    public PermissionException() {
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }

    public PermissionException newInstance(String msgFormat, Object... args) {
        return new PermissionException(this.code, msgFormat, args);
    }

    public PermissionException(String message) {
        super(message);
    }


}
