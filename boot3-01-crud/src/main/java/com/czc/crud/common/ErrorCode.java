package com.czc.crud.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    PARAM_ERROR(40001, "参数校验失败"),
    UNAUTHORIZED(40101, "未登录"),
    FORBIDDEN(40301, "无权限"),
    NOT_FOUND(40401, "资源不存在"),
    DUPLICATE(40901, "重复提交"),
    BIZ_ERROR(50001, "业务处理失败"),
    SYSTEM_ERROR(50000, "系统繁忙");

    private final int code;
    private final String message;
}