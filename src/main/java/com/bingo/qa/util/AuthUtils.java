package com.bingo.qa.util;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenghongyi
 * @date 2023/08/06
 */

public class AuthUtils {

    private static final String AUTH_METHOD   = "MWS";

//    private static final String CLIENT_ID     = "com.sankuai.grocerydwtest.datalineage";
//
//    private static final String CLIENT_SECRET = "72992b42e1d08f3501a5434267463cf9";

    private static final String MAC_NAME      = "HmacSHA1";

    private static final String ENCODING      = "UTF-8";

    /**
     * @see "https://123.sankuai.com/km/page/28107651"
     * MTAuth Description:
     * Authorization = "MWS" + " " + client_id + ":" + signature;
     * signature = base64( HMAC-SHA1(( string_to_sign, client_secret ) ) );
     * string_to_sign = HTTP-Verb + " " + REQUEST_URI + "\n" + Date;
     * HTTP-Verb = GET | POST | DELETE | PUT
     * REQUEST_URI : 指请求URI，不包含 ? 以及 query_string
     * Date : 是 Http Header 里的 Date 字段;
     */

    public static Map<String, String> getBasicAuthHeader(String client_id,String client_secret,String method, String url)
            throws Exception {
        String date = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneOffset.UTC));
        String signature = sign(date, method, url,client_secret);
        String authorization = AUTH_METHOD + ' ' + client_id + ':' + signature;
        Map<String, String> header = new HashMap<>(2);
        header.put("Date", date);
        header.put("Authorization", authorization);
        return header;
    }

    static String sign(String date, String method, String url,String client_secret) throws Exception {
        String path = new URL(url).getPath();
//        String contentToSign = StringUtils.toUpperCase(method) + " " + path + "\n" + date;
        String contentToSign = method.toUpperCase() + " " + path + "\n" + date;
        return base64encode(hMacSha1Encrypt(contentToSign, client_secret));
    }

    static byte[] hMacSha1Encrypt(String encryptText, String encryptKey) throws Exception {
        byte[] data = encryptKey.getBytes(ENCODING);
        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        //生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        //用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(ENCODING);
        //完成 Mac 操作
        return mac.doFinal(text);
    }

    static String base64encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

}
