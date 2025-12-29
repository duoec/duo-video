package com.duoec.video.jy.utils;

public class UuidUtils {
    private UuidUtils() {
    }

    public static String next() {
        return java.util.UUID.randomUUID().toString();
    }
}
