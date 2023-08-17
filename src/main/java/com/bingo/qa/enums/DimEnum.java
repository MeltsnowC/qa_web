package com.bingo.qa.enums;

import com.meituan.servicecatalog.api.annotations.FieldDoc;
import lombok.Getter;

/**
 * @ClassName AnalysisEngine
 * @Description
 * @Author wangfeng78
 * @Date 2022/8/16 11:31
 */

@Getter
public enum DimEnum {
    /**
     * @Description 维度枚举
     * @Author wangfeng78
     * @Date 2022/8/16 12:14
     **/
    @FieldDoc(
            description = "大区"
    )
    DRILL_REGION("region_id", "province_id"),

    @FieldDoc(
            description = "省份"
    )
    DRILL_PROVINCE("province_id", "city_id"),

    @FieldDoc(
            description = "一级品类"
    )
    DRILL_CATEGORY1("category1_id", "category2_id"),

    @FieldDoc(
            description = "二级品类"
    )
    DRILL_CATEGORY2("category2_id", "category3_id"),

    @FieldDoc(
            description = "业务模式"
    )
    DRILL_COOPERATION("cooperation_type_code", "cooperation_type_code");

    private String dim;
    private String stepDim;

    DimEnum(String dim, String stepDim) {
        this.dim = dim;
        this.stepDim = stepDim;
    }

    public static DimEnum getDimEnumByDim(String dim) {
        /**
         * @Description 获取枚举
         * @MethodName getDimEnumByDim
         * @Author wangfeng78
         * @Date 2022/8/16 12:14
         * @return com.sankuai.grocery.ae.server.enums.DimEnum
         * @Parm [dim]
         **/
        for(DimEnum dimEnum : DimEnum.values()) {
            if (dimEnum.getDim().equals(dim)) {
                return dimEnum;
            }
        }
        return null;
    }
}
