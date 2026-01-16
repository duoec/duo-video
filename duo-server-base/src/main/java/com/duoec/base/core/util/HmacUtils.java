package com.duoec.base.core.util;

import com.duoec.base.exceptions.DuoServiceException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author xuwenzhen
 */
public class HmacUtils {
    private static final String STR_HMAC_SHA_256 = "HmacSHA256";

    private HmacUtils() {
    }

    public static String calculateHmacSha256(String key, String message) {
        Mac mac;
        try {
            mac = Mac.getInstance(STR_HMAC_SHA_256);
        } catch (NoSuchAlgorithmException e) {
            throw new DuoServiceException("HmacSHA256 not supported", e);
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), STR_HMAC_SHA_256);
        try {
            mac.init(secretKeySpec);
        } catch (InvalidKeyException e) {
            throw new DuoServiceException("Invalid key", e);
        }
        return bytesToHex(mac.doFinal(message.getBytes()));
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
