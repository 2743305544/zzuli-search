package com.searchback.handler.exption;


import com.searchback.ResponseResult;
import com.searchback.excption.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.searchback.enums.AppHttpCodeEnum;

/**
 * @author 34011 shiyi
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SystemException.class)
    public ResponseResult systemException(SystemException e) {
        log.error(e.getMessage());
        return ResponseResult.errorResult(e.getCode(), e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e) {
        log.error("出现了异常！：",e);
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode() ,e.getMessage());
    }
}
