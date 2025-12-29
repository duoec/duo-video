package com.duoec.base.core.util;

import java.util.regex.Pattern;

/**
 * @author xuwenzhen
 */
public class NumberUtils {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^\\d+$");
    private static final Pattern NATURAL_PATTERN = Pattern.compile("0|[1-9]\\d*");


    private NumberUtils() {

    }

    public static boolean isNatural(String str) {
        return str != null && NATURAL_PATTERN.matcher(str).matches();
    }

    public static boolean isNumeric(String str) {
        return NUMBER_PATTERN.matcher(str).matches();
    }
}
