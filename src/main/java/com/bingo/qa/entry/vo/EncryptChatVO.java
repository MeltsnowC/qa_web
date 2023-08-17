package com.bingo.qa.entry.vo;

import lombok.Data;

/**
 * @ClassName AnalysisEngine
 * @Description
 * @Author wangfeng78
 * @Date 2022/10/13 16:15
 */

@Data
public class EncryptChatVO {
    String mode;
    String priKey;
    String pubKey;
    String text;
}
