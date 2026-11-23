package com.czc.crud.common;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result<T> {
    private int code;
    private String message;
    private T data;
    private long timestamp;

    public static <T> Result<T> ok(T data) {
        return new Result<T>(0, "ok", data,System.currentTimeMillis());
    }

    public static<T>Result<T>fail(ErrorCode ec) {
        return new Result<>(ec.getCode(), ec.getMessage(),null, System.currentTimeMillis());
    }
    public static <T> Result<T> fail(ErrorCode ec, T data) {
        return new Result<>(ec.getCode(), ec.getMessage(), data, System.currentTimeMillis());
    }
}
