package com.duoec.video.project.material;

import com.duoec.base.exceptions.DuoServiceException;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class TextStyle implements Serializable {
    /**
     * 字体大小
     *
     * @demo 16
     */
    private Integer fontSize;

    /**
     * 字体加粗，默认false
     *
     * @demo true
     */
    private Boolean bold;

    /**
     * 斜体，默认false
     *
     * @demo true
     */
    private Boolean italic;

    /**
     * 下划线，默认false
     *
     * @demo true
     */
    private Boolean underline;

    /**
     * 文本对齐 0=左对齐 1=水平居中 2=右对齐 3=顶对齐 4=底对齐 5=水平分布 6=垂直分布
     *
     * @demo 1
     */
    private Integer textAlign;

    /**
     * 字体名称
     *
     * @demo 微软雅黑
     */
    private String fontName;

    /**
     * 填充颜色
     *
     * @demo #FFFFFF
     */
    private String fillColor;

    /**
     * 行间距
     */
    private Integer textLineWidth;

    /**
     * 字间距
     */
    private Integer textWordWidth;

    /**
     * 描边颜色
     *
     * @demo #FF0000
     */
    private String strokeColor;

    /**
     * 描边宽度
     */
    private Integer strokeWidth;

    /**
     * 背景颜色
     *
     * @demo #FF0000
     */
    private String backgroundColor;

    /**
     * 背景透明度，100表示不透明，0表示全透明，取值范围[0, 100]
     *
     * @demo 100
     */
    private Integer backgroundOpacity;

    /**
     * 背景圆角，取值范围[0, 100]，单位：百分比
     *
     * @demo 0
     */
    private Integer backgroundRadius;

    /**
     * 背景高度，取值范围[0, 100]，单位：百分比
     *
     * @demo 19
     */
    private Integer backgroundHeight;

    /**
     * 背景宽度，取值范围[0, 100]，单位：百分比
     *
     * @demo 14
     */
    private Integer backgroundWidth;

    /**
     * 左右偏移，取值范围[0, 100]，单位：百分比
     *
     * @demo 50
     */
    private Integer backgroundOffsetX;

    /**
     * 上下偏移，取值范围[0, 100]，单位：百分比
     *
     * @demo 50
     */
    private Integer backgroundOffsetY;

    /**
     * 发光颜色
     *
     * @demo #FF0000
     */
    private String shineColor;

    /**
     * 发光强度，取值范围[10, 100]
     *
     * @demo 10
     */
    private Integer shineStrength;

    /**
     * 发光范围，取值范围[10, 100]
     *
     * @demo 10
     */
    private Integer shineWidth;

    /**
     * 发光水平角度，取值范围[-50, 50]
     *
     * @demo 0
     */
    private Integer shineAngleHorizon;

    /**
     * 发光垂直角度，取值范围[-50, 50]
     *
     * @demo 0
     */
    private Integer shineAngleVertical;

    /**
     * 阴影颜色
     *
     * @demo #FF0000
     */
    private String shadowColor;

    /**
     * 阴影透明度，100表示不透明，0表示全透明，取值范围[0, 100]
     *
     * @demo 100
     */
    private Integer shadowOpacity;

    /**
     * 阴影模糊度，100表示不透明，0表示全透明，取值范围[0, 100]
     *
     * @demo 15
     */
    private Integer shadowVague;

    /**
     * 阴影距离，100表示不透明，0表示全透明，取值范围[0, 100]
     *
     * @demo 5
     */
    private Integer shadowWidth;

    /**
     * 阴影角度，取值范围[-180, 180]
     *
     * @demo 0
     */
    private Integer shadowAngle;

    /**
     * 弯曲程度，取值范围[-360, 360]
     *
     * @demo 0
     */
    private Integer bend;

    /**
     * 花字ID
     */
    private Long flowerId;

    private static final Map<String, Field> FIELD_MAP = new HashMap<>();

    public TextWord toTextWidgetWord(int start, int length) {
        TextWord word = new TextWord()
                .setIndex(start)
                .setLength(length);

        initFieldMap();

        //将 当前的属性值复制到word上去
        FIELD_MAP.forEach((fieldName, field) -> {
            Object val = null;
            try {
                val = field.get(this);
                if (val == null) {
                    return;
                }
                field.set(word, val);
            } catch (Exception e) {
                throw new RuntimeException("设置样式失败：" + field + ": " + val, e);
            }
        });

        return word;
    }

    private void initFieldMap() {
        if (!FIELD_MAP.isEmpty()) {
            return;
        }
        for (Field field : this.getClass().getDeclaredFields()) {
            //判断是否是静态方法，则丢弃
            if ((field.getModifiers() & Modifier.STATIC) == Modifier.STATIC) {
                continue;
            }

            FIELD_MAP.put(field.getName(), field);
            field.setAccessible(true);
        }
    }

    public void mergeStyle(TextWord word) {
        initFieldMap();

        FIELD_MAP.forEach((fieldName, field) -> {
            Object val = null;
            try {
                val = field.get(word);
                if (val == null) {
                    return;
                }
                field.set(this, val);
            } catch (Exception e) {
                throw new DuoServiceException("设置样式失败：" + field + ": " + val, e);
            }
        });
    }
}
