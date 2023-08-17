package com.bingo.qa.enums;

import com.meituan.servicecatalog.api.annotations.FieldDoc;
import lombok.Getter;

/**
 * @ClassName AnalysisEngine
 * @Description
 * @Author wangfeng78
 * @Date 2022/8/24 14:32
 */

@Getter
public enum FormulaType {

    @FieldDoc(
            description = "常量"
    )
    CONSTANTS("constants"),
    @FieldDoc(
            description = "指标"
    )
    CODE("code"),
    @FieldDoc(
            description = "公式"
    )
    FORMULA("formula"),
    ;

    private String desc;

    FormulaType(String desc) {
        this.desc = desc;
    }

    public static FormulaType getFormulaType(String desc) {
        for(FormulaType formulaType : FormulaType.values()) {
            if (formulaType.getDesc().equals(desc)) {
                return formulaType;
            }
        }
        return null;
    }
}
