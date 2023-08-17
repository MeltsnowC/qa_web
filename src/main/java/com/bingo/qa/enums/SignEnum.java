package com.bingo.qa.enums;

import lombok.Getter;

/**
 * @ClassName AnalysisEngine
 * @Description
 * @Author wangfeng78
 * @Date 2022/8/23 16:09
 */

@Getter
public enum SignEnum {
    ADD("+"),
    SUB("-"),
    MULTI("*"),
    DIV("/"),
    ;

    private String sign;

    SignEnum(String sign) {
        this.sign = sign;
    }

    public static SignEnum getSignEnum(String sign) {
        for(SignEnum signEnum : SignEnum.values()) {
            if (signEnum.getSign().equals(sign)) {
                return signEnum;
            }
        }
        return null;
    }
}
