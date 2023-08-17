package com.bingo.qa.enums;

import lombok.Getter;

/**
 * @ClassName AnalysisEngine
 * @Description
 * @Author wangfeng78
 * @Date 2022/5/20 14:39
 */

@Getter
public enum DimAttrEnum {
    /** 枚举 */
    ADD(0, "add", "可加"),
    UV(1, "uv", "去重"),
    RATIO_ADD(2, "ratio_add", "比值-可加"),
    RATIO_UV(3, "ratio_uv", "比值-去重"),
    OTHER(4, "other", "其他"),
    ;

    private final Integer code;
    private final String type;
    private final String description;

    DimAttrEnum(Integer code, String type, String description) {
        this.code = code;
        this.type = type;
        this.description = description;
    }

    public static DimAttrEnum getEnumByType(String type) {
        for(DimAttrEnum dimAttrEnum : DimAttrEnum.values()) {
            if (dimAttrEnum.getType().equals(type)) {
                return dimAttrEnum;
            }
        }
        return null;
    }
}
