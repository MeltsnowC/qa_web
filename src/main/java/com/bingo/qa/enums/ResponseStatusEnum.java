package com.bingo.qa.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName grocery-dtp-service
 * @Description
 * @Author wangfeng78
 * @Date 2022/3/10 17:17
 */

public enum ResponseStatusEnum {
    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数错误"),
    INTERNAL_ERROR(2, "服务器内部错误");

    private int code;   //状态码
    private String msg; //状态说明

    ResponseStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //构建内部静态映射
    private static Map<Integer, ResponseStatusEnum> enumMap = new HashMap<>();

    static {
        Arrays.stream(ResponseStatusEnum.values())
                .forEach(status -> enumMap.put(status.code, status));
    }

    /**
     * 根据code转换为枚举
     *
     * @param code
     * @return
     */
    public static ResponseStatusEnum of(Integer code) {
        return enumMap.get(code);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
