package com.bingo.qa.controller;


import com.bingo.qa.entry.vo.EncryptChatVO;
import com.bingo.qa.response.Response;
import com.bingo.qa.util.RsaUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AnalysisEngine
 * @Description
 * @Author wangfeng78
 * @Date 2022/10/13 16:13
 */

@RestController
@RequestMapping("/api/encrypt")
public class EncryptedController {

    @RequestMapping(path = {"/chat"}, method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public Response encryptChat(@RequestBody EncryptChatVO encryptChatVO) {
        try {
            RsaUtils.RsaCipherEnum rsaCipherEnum = RsaUtils.RsaCipherEnum.getEnum(encryptChatVO.getMode());
            if (rsaCipherEnum == null) {
                return Response.customError(-1, "mode传输错误");
            }
            switch (rsaCipherEnum) {
                case GENERATE:
                    return Response.success(RsaUtils.generateRsaKey());
                case ENCRYPT:
                    return Response.success(RsaUtils.encrypt(encryptChatVO.getText(), encryptChatVO.getPubKey()));
                case DECRYPT:
                    return Response.success(RsaUtils.decrypt(encryptChatVO.getText(), encryptChatVO.getPriKey()));
                default:
                    return Response.customError(-1, "mode传输错误");
            }
        } catch (Exception e) {
            return Response.customError(-1, e.getMessage());
        }
    }
}
