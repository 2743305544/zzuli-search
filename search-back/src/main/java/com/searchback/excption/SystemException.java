package com.searchback.excption;

import com.searchback.enums.AppHttpCodeEnum;
import lombok.Getter;

/**
 * @author 34011 shiyi
 */
@Getter
public class SystemException extends RuntimeException {
    private int code;
    private String msg;

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMessage());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMessage();
    }
}
