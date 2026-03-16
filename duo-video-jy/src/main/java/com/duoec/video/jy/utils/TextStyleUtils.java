package com.duoec.video.jy.utils;

import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.jy.JianyingProjectBuildState;
import com.duoec.video.project.material.StyleMaterial;
import com.duoec.video.project.material.BaseTextMaterial.TextStyle;
import com.duoec.video.project.material.BaseTextMaterial.TextWord;
import com.google.common.collect.Sets;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TextStyleUtils {

    private static final Map<String, Field> FIELD_MAP = new HashMap<>();

    private static final Set<String> EXCLUDE_TEXT_STYLE_FIELD_NAMES = Sets.newHashSet("length", "index");

    static {
        initFieldMap();
    }

    public static TextWord toTextWidgetWord(TextStyle textStyle, int start, int length) {
        TextWord word = new TextWord()
                .setIndex(start)
                .setLength(length);

        mergeTextStyle(word, textStyle);
        return word;
    }

    public static TextStyle mergeTextStyle(TextStyle target, TextStyle word) {
        //将 当前的属性值复制到word上去
        FIELD_MAP.forEach((fieldName, field) -> {
            Object val = null;
            try {
                val = field.get(word);
                if (val == null) {
                    return;
                }
                field.set(target, val);
            } catch (Exception e) {
                throw new RuntimeException("设置样式失败：" + field + ": " + val, e);
            }
        });
        return target;
    }

    private static void initFieldMap() {
        if (!FIELD_MAP.isEmpty()) {
            return;
        }
        for (Field field : TextStyle.class.getDeclaredFields()) {
            String name = field.getName();
            if (EXCLUDE_TEXT_STYLE_FIELD_NAMES.contains(name)) {
                continue;
            }

            //判断是否是静态方法，则丢弃
            if ((field.getModifiers() & Modifier.STATIC) == Modifier.STATIC) {
                continue;
            }

            FIELD_MAP.put(name, field);
            field.setAccessible(true);
        }
    }

    public static void mergeStyle(JianyingProjectBuildState state, TextWord target, TextWord word) {
        Long styleId = word.getStyleId();
        if (styleId != null) {
            StyleMaterial styleMaterial = state.getMaterial(styleId);
            if (styleMaterial != null && styleMaterial.getStyle() != null) {
                mergeTextStyle(target, styleMaterial.getStyle());
            }
        }
        FIELD_MAP.forEach((fieldName, field) -> {
            Object val = null;
            try {
                val = field.get(word);
                if (val == null) {
                    return;
                }
                field.set(target, val);
            } catch (Exception e) {
                throw new DuoServiceException("设置样式失败：" + field + ": " + val, e);
            }
        });
    }
}
