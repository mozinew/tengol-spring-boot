package com.tengol.practice.sb.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RestResult
 *
 * @author dongrui
 * @date 2021/5/28 13:01
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResult<T> {
    public static final Integer CODE_SUCCESS = 0;
    public static final Integer CODE_FAIL = -1;
    public static final String MSG_SUCCESS = "success";
    public static final String MSG_FAIL = "fail";

    @Builder.Default
    private int code = CODE_SUCCESS;

    @Builder.Default
    private String msg = MSG_SUCCESS;

    private T data;

    public static RestResult fail() {
        return RestResult.builder().code(CODE_FAIL).msg(MSG_FAIL).build();
    }

    public static RestResult fail(String msg) {
        return RestResult.builder().code(CODE_FAIL).msg(msg).build();
    }

    public static RestResult fail(int code, String msg) {
        return RestResult.builder().code(code).msg(msg).build();
    }

    public static RestResult success() {
        return RestResult.builder().code(CODE_SUCCESS).msg(MSG_SUCCESS).build();
    }

    public static RestResult success(Object data) {
        return RestResult.builder().code(CODE_SUCCESS).msg(MSG_SUCCESS).data(data).build();
    }
}
