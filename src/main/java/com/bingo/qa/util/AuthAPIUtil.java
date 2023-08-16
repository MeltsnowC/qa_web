package com.bingo.qa.util;

import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName grocery-dtp-service
 * @Description
 * @Author chenghongyi
 * @Date 2022/4/24 16:40
 */

public class AuthAPIUtil {

    private static final DateFormat DF = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    static {
        DF.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    private synchronized static String getDateString() {
        return DF.format(new Date());
    }

    private static String getSignature(String data, String secret) {
        String result;
        try {
            SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), HMAC_SHA1_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            result = Base64.encodeBase64String(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate HMAC : ", e);
        }
        return result;
    }

    /**
     * 签名算法生成方法
     */
    public static Map<String, String> getSignHeaders(String url, String appKey, String token, String method) {
        if (token == null || token.isEmpty()) {
            return Maps.newHashMap();
        }
        if (url == null || !url.contains("//")) {
            return Maps.newHashMap();
        }
        try {
            String uri = new URL(url).getPath();
            String date = getDateString();
            String signature = getSignature(String.format("%s %s\n%s", method, uri, date), token);
            String authorization = String.format("MWS %s:%s", appKey, signature);
            HashMap<String, String> signHeaderMap = Maps.newHashMap();
            signHeaderMap.put("Date", date);
            signHeaderMap.put("Authorization", authorization);
            return signHeaderMap;
        } catch (MalformedURLException e) {
            return Maps.newHashMap();
        }
    }
}