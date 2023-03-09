package com.gjk.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Result
 *
 * @author: gaojiankang
 * @date: 2022/9/27 16:55
 * @description:
 */
@Data
@AllArgsConstructor
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

    public static Result<String> createError(Integer code, String message){
        return new Result<>(code, message, null);
    }

}
