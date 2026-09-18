package com.module.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseData<T> {
    private final String resultCode;
    private final String msg;
    private final T data;

    public ResponseData(String resultCode, String msg) {
        this(resultCode, msg, null);
    }
}
