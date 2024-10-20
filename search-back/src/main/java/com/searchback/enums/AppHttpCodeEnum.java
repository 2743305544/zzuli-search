package com.searchback.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 34011 shiyi
 */

@Getter
public enum AppHttpCodeEnum {

    SUCCESS(200,"操作成功"),
    SYSTEM_ERROR(500,"出现错误");
    int code;
    String message;
    AppHttpCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
