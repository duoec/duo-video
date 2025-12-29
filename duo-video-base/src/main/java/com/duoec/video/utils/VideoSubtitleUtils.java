package com.duoec.video.utils;

import com.duoec.base.core.DuoServerConsts;

public class VideoSubtitleUtils {
    public static int getTextWidth(String w) {
        int maxLen = -1;
        for (String word : w.split(DuoServerConsts.TURN_LINE)) {
            int l = 0;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (c >= 0x4e00 && c <= 0x9fbb) {
                    l += 2;
                } else {
                    l += 1;
                }
            }
            maxLen = Math.max(maxLen, l);
        }

        return maxLen;
    }
}
