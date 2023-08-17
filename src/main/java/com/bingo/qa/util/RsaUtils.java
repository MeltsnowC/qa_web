package com.bingo.qa.util;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

/**
 * @ClassName AnalysisEngine
 * @Description
 * @Author wangfeng78
 * @Date 2022/10/13 15:46
 */

@Slf4j
public class RsaUtils {
    private static final int DEFAULT_RSA_KEY_SIZE = 2048;
    private static final String KEY_ALGORITHM = "RSA";
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    private static final String PUBLIC_KEY = "publicKey";
    private static final String PRIVATE_KEY = "privateKey";

    @Getter
    public enum RsaCipherEnum {
        GENERATE("generate"),
        DECRYPT("decrypt"),
        ENCRYPT("encrypt"),
        ;
        String desc;

        RsaCipherEnum(String desc) {
            this.desc = desc;
        }

        public static RsaCipherEnum getEnum(String mode) {
            for (RsaCipherEnum loop : RsaCipherEnum.values()) {
                if (loop.getDesc().equals(mode)) {
                    return loop;
                }
            }
            return null;
        }
    }

    @SneakyThrows
    public static Map<String, String> generateRsaKey() {
        int keySize = 2048;
        Map<String, String> keyMap = Maps.newHashMap();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(keySize, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        keyMap.put(PUBLIC_KEY, new String(Base64.encodeBase64(keyPair.getPublic().getEncoded())));
        keyMap.put(PRIVATE_KEY, new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded())));
        return keyMap;
    }

    @SneakyThrows
    public static String decrypt(String var0, String privateKey) {
        byte[] inputByte = Base64.decodeBase64(var0.getBytes(StandardCharsets.UTF_8));
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey pk = (RSAPrivateKey) KeyFactory.getInstance(KEY_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, pk);
        return new String(cipher.doFinal(inputByte));
    }

    @SneakyThrows
    public static String encrypt(String var0, String publicKey) {
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeBase64String(cipher.doFinal(var0.getBytes(StandardCharsets.UTF_8)));
    }
}
