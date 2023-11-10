package com.ueumd.tech.common.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.ueumd.tech.common.vo.ResponseDTO;
import io.jsonwebtoken.JwtException;
import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description: 全局异常拦截
 * Author: hsd
 * Date: 2023-06-09 10:00
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler() {
    }

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> exceptionHandler(HttpServletRequest request, Exception exception) {
        if (exception instanceof HttpRequestMethodNotSupportedException) {
            LOGGER.warn("【http请求异常】" + exception.getMessage(), exception);
            return new ResponseEntity("请求方式非法", HttpStatus.BAD_REQUEST);
        } else if (exception instanceof HttpMessageNotReadableException) {
            LOGGER.warn("【http请求json参数有误】" + exception.getMessage(), exception);
            return new ResponseEntity("json参数有误", HttpStatus.BAD_REQUEST);
        } else if (exception instanceof ServletRequestBindingException) {
            LOGGER.warn("【servlet请求异常】" + exception.getMessage(), exception);
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        } else if (exception instanceof MethodArgumentTypeMismatchException) {
            LOGGER.warn("【参数异常】" + exception.getMessage(), exception);
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        } else if (exception instanceof JsonParseException) {
            LOGGER.warn("【解析异常】" + exception.getMessage(), exception);
            return new ResponseEntity("Json解析错误，请检查参数！", HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (exception instanceof ClientAbortException) {
            LOGGER.warn("【响应未结束】" + exception.getMessage(), exception);
            return new ResponseEntity("响应未结束!", HttpStatus.OK);
        } else {
            ResponseDTO responseDto;
            if (!(exception instanceof MethodArgumentNotValidException)) {
                if (exception instanceof PermissionException) {
                    LOGGER.warn("【权限异常】" + exception.getMessage(), exception);
                    PermissionException permissionException = (PermissionException)exception;
                    responseDto = new ResponseDTO();
                    responseDto.setErrCode(permissionException.getCode());
                    responseDto.setMessage(permissionException.getMessage());
                    return new ResponseEntity(responseDto, HttpStatus.UNAUTHORIZED);
                } else if (exception instanceof JwtException) {
                    LOGGER.warn("【权限异常】" + exception.getMessage(), exception);
                    responseDto = new ResponseDTO();
                    responseDto.setErrCode(HttpStatus.UNAUTHORIZED.value());
                    responseDto.setMessage("无效的用户信息");
                    return new ResponseEntity(responseDto, HttpStatus.UNAUTHORIZED);
                } else if (exception instanceof BusinessException) {
                    LOGGER.info("【业务异常】" + exception.getMessage(), exception);
                    BusinessException bizException = (BusinessException)exception;
                    responseDto = new ResponseDTO();
                    responseDto.setErrCode(bizException.getCode());
                    responseDto.setMessage(bizException.getMessage());
                    responseDto.setTraceId(bizException.getTraceId());
                    return new ResponseEntity(responseDto, HttpStatus.OK);
                } else if (exception instanceof MaxUploadSizeExceededException) {
                    LOGGER.warn("【文件异常】" + exception.getMessage(), exception);
                    return new ResponseEntity("文件大小超出限制!", HttpStatus.BAD_REQUEST);
                } else if (exception instanceof RuntimeException) {
                    LOGGER.info("【服务异常】" + exception.getMessage(), exception);
                    responseDto = new ResponseDTO();
                    responseDto.setErrCode(ExceptionCode.FEIGN_ERROR.getCode());
                    responseDto.setMessage(exception.getMessage());
                    return new ResponseEntity(responseDto, HttpStatus.OK);
                } else {
                    LOGGER.error("【服务异常】" + exception.getMessage(), exception);
                    return new ResponseEntity("系统繁忙,请稍后再试!", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                LOGGER.info("【参数异常】" + exception.getMessage(), exception);
                MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException)exception;
                responseDto = new ResponseDTO();
                BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
                String resultMessage = "";
                List<FieldError> fieldErrors = bindingResult.getFieldErrors();

                for(int i = 0; i < fieldErrors.size(); ++i) {
                    if (fieldErrors.size() != 1 && i != fieldErrors.size() - 1) {
                        resultMessage = resultMessage + fieldErrors.get(i).getDefaultMessage() + ", ";
                    } else {
                        resultMessage = resultMessage + fieldErrors.get(i).getDefaultMessage();
                    }
                }
                responseDto.setErrCode(HttpStatus.BAD_REQUEST.value());
                responseDto.setMessage(resultMessage);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }
    }
}
