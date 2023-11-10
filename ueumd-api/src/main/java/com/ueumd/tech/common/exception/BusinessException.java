package com.ueumd.tech.common.exception;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-05 18:47
 */
public class BusinessException extends RuntimeException {

    public static final BusinessException PARAM_DELETION = new BusinessException(90040012, "参数缺失", new Object[0]);

    protected String msg;
    protected int code;
    protected String traceId;

    public BusinessException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }

    public BusinessException(String traceId, int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
        this.traceId = traceId;
    }

    public BusinessException() {
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getTraceId() {
        return traceId;
    }

    public BusinessException newInstance(String msgFormat, Object... args) {
        return new BusinessException(this.code, msgFormat, args);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause, String traceId) {
        super(cause);
        this.traceId = traceId;
    }

    public BusinessException(Throwable cause, String traceId, int code) {
        super(cause);
        this.traceId = traceId;
        this.code = code;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(ExceptionCode exceptionCode){

        super(exceptionCode.getMessage());
        this.code = exceptionCode.getCode();
        this.msg = exceptionCode.getMessage();
    }

}
