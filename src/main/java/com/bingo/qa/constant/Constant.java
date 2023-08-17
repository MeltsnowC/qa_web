package com.bingo.qa.constant;

/**
 * @ClassName AnalysisEngine
 * @Description
 * @Author wangfeng78
 * @Date 2023/3/28 10:45
 */

public interface Constant {
    String BASE = "base";
    String CUSTOM_PARAMS = "customParams";
    String DRILL_DIM = "drill_dim";

    String Index_Info = "{\"pcs_avg_price\":{\"indexCode\":\"pcs_avg_price\",\"indexName\":\"件均价\",\"indexType\":\"index\",\"dataType\":\"FLOAT\"},\"sale_amt\":{\"indexCode\":\"sale_amt\",\"indexName\":\"销售额\",\"indexType\":\"index\",\"dataType\":\"NUMBER\"},\"sale_num\":{\"indexCode\":\"sale_num\",\"indexName\":\"销售件数\",\"indexType\":\"index\",\"dataType\":\"NUMBER\"}}";
    String Index_Tree = "[{\"levelId\":\"sale_amt\",\"levelName\":\"销售额\",\"levelPId\":\"pcs_avg_price\",\"operator\":\"*\"},{\"levelId\":\"sale_num\",\"levelName\":\"销售件数\",\"levelPId\":\"pcs_avg_price\",\"operator\":\"/\"}]";
    String Dim_Attr_Meta = "{\"dimAttrMetas\":[{\"indexCode\":\"pcs_avg_price\",\"dimPairGroups\":[{\"groupId\":\"7\",\"groupName\":\"二级品类\",\"dimPairs\":[{\"dimId\":\"7\",\"alias\":\"二级品类\",\"dimIdCode\":\"category2_id\",\"dimNameCode\":\"category2_name\"}]}],\"weightIndex\":\"sale_num\",\"indexType\":\"RATIO\",\"weightIndexType\":\"ADD\"},{\"indexCode\":\"sale_amt\",\"dimPairGroups\":[{\"groupId\":null,\"groupName\":null,\"dimPairs\":[{\"dimId\":\"7\",\"alias\":\"二级品类\",\"dimIdCode\":\"category2_id\",\"dimNameCode\":\"category2_name\"},{\"dimId\":\"14\",\"alias\":\"一级来源\",\"dimIdCode\":\"external_entr1_name\",\"dimNameCode\":\"external_entr1_name\"},{\"dimId\":\"13\",\"alias\":\"渠道\",\"dimIdCode\":\"channel2_id\",\"dimNameCode\":\"channel2_name\"}]}],\"weightIndex\":null,\"indexType\":\"ADD\",\"weightIndexType\":\"ADD\"}]}";
}
