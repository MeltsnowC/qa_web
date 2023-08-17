package com.bingo.qa.enums;

import lombok.Getter;

/**
 * @ClassName AnalysisEngine
 * @Description
 * @Author wangfeng78
 * @Date 2023/1/11 11:24
 */

@Getter
public enum ThresholdMode {
    ONE("10/90"),
    TWO("5/95"),
    THREE("15/85"),
    FOUR("20/80"),
    FIVE("25/75")
    ;
    private final String rule;

    ThresholdMode(String rule) {
        this.rule = rule;
    }

    public static ThresholdMode getMode(String rule) {
        for(ThresholdMode thresholdMode : ThresholdMode.values()) {
            if (thresholdMode.getRule().equals(rule)) {
                return thresholdMode;
            }
        }
        return ONE;
    }
}
