package com.bingo.qa.enums;

import com.meituan.servicecatalog.api.annotations.FieldDoc;
import lombok.Getter;

/**
 * @ClassName AnalysisEngine
 * @Description
 * @Author wangfeng78
 * @Date 2022/8/15 19:48
 */

@Getter
public enum TransactionEnum {
    /**
     * 异动类型
     **/
    @FieldDoc(
            description = "值异动"
    )
    VALUE_TRANSACTION(3, "value"),

    @FieldDoc(
            description = "波动异动"
    )
    WAVE_TRANSACTION(4, "wave"),

    @FieldDoc(
            description = "趋势异动"
    )
    TREND_TRANSACTION(2, "trend"),

    @FieldDoc(
            description = "超额异动"
    )
    EXCESS_TRANSACTION(1, "excess"),

    @FieldDoc(
            description = "无异动"
    )
    NONE_TRANSACTION(0, "trend"),
    ;

    Integer weight;
    String type;

    TransactionEnum(Integer weight, String type) {
        this.weight = weight;
        this.type = type;
    }

}
