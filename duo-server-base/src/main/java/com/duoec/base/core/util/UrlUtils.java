package com.duoec.base.core.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author xuwenzhen
 * @since 2021/9/17 12:12 PM
 */
public class UrlUtils {
    private UrlUtils() {
    }

    public static String encode(String url) {
        try {
            return URLEncoder.encode(url, String.valueOf(StandardCharsets.UTF_8)).replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            return url;
        }
    }

    public static String decode(String url) {
        try {
            return URLDecoder.decode(url, String.valueOf(StandardCharsets.UTF_8));
        } catch (UnsupportedEncodingException e) {
            return url;
        }
    }
}
