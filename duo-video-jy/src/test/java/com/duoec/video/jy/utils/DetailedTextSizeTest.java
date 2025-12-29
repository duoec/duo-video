package com.duoec.video.jy.utils;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.File;

/**
 * 详细的文本尺寸测试 - 用于分析误差来源
 */
public class DetailedTextSizeTest {

    public static void main(String[] args) throws Exception {
        String fontPath = "/Users/xuwenzhen/Movies/JianyingPro/User Data/Cache/effect/7070430593925779981/ced7a98a9bcc8984318a2c215192b0c5/楚辰体.ttf";
        File fontFile = new File(fontPath);

        if (!fontFile.exists()) {
            System.out.println("字体文件不存在: " + fontPath);
            return;
        }

        Font baseFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        String text1 = "前方";
        double size1 = 39.266;
        String text2 = "高能";
        double size2 = 44.266;
        double italicDegree = 10;
        double letterSpacing = 0; // 从content.json获取

        System.out.println("=== 测试不同的FontRenderContext配置 ===\n");

        // 测试1: null transform, antialiasing, fractional metrics
        testWithFRC("配置1: AA=true, FM=true",
            new FontRenderContext(null, true, true),
            baseFont, text1, size1, text2, size2, italicDegree, letterSpacing);

        // 测试2: identity transform
        testWithFRC("配置2: AA=true, FM=true, Identity Transform",
            new FontRenderContext(new AffineTransform(), true, true),
            baseFont, text1, size1, text2, size2, italicDegree, letterSpacing);

        // 测试3: 不同的抗锯齿设置
        testWithFRC("配置3: AA=false, FM=true",
            new FontRenderContext(null, false, true),
            baseFont, text1, size1, text2, size2, italicDegree, letterSpacing);

        // 测试4: 不同的fractional metrics设置
        testWithFRC("配置4: AA=true, FM=false",
            new FontRenderContext(null, true, false),
            baseFont, text1, size1, text2, size2, italicDegree, letterSpacing);

        System.out.println("\n=== 测试不同的高度计算方法 ===\n");
        testDifferentHeightCalculations(baseFont, text1, size1, text2, size2, italicDegree);

        System.out.println("\n=== 测试段落间距的影响 ===\n");
        testSegmentSpacing(baseFont, text1, size1, text2, size2, italicDegree);
    }

    private static void testWithFRC(String label, FontRenderContext frc,
                                   Font baseFont, String text1, double size1,
                                   String text2, double size2,
                                   double italicDegree, double letterSpacing) {

        System.out.println(label + ":");

        // 计算第一段
        Font font1 = baseFont.deriveFont((float) size1);
        AffineTransform transform1 = AffineTransform.getShearInstance(-Math.tan(Math.toRadians(italicDegree)), 0);
        Font italicFont1 = font1.deriveFont(transform1);
        TextLayout layout1 = new TextLayout(text1, italicFont1, frc);
        Rectangle2D bounds1 = layout1.getBounds();
        double width1 = bounds1.getWidth();
        double height1 = layout1.getAscent() + layout1.getDescent();

        // 计算第二段
        Font font2 = baseFont.deriveFont((float) size2);
        AffineTransform transform2 = AffineTransform.getShearInstance(-Math.tan(Math.toRadians(italicDegree)), 0);
        Font italicFont2 = font2.deriveFont(transform2);
        TextLayout layout2 = new TextLayout(text2, italicFont2, frc);
        Rectangle2D bounds2 = layout2.getBounds();
        double width2 = bounds2.getWidth();
        double height2 = layout2.getAscent() + layout2.getDescent();

        double totalWidth = width1 + width2;
        double maxHeight = Math.max(height1, height2);

        // 计算缩放系数
        double expectedWidth = 700.5253295898438;
        double expectedHeight = 258.21832275390625;
        double widthRatio = expectedWidth / totalWidth;
        double heightRatio = expectedHeight / maxHeight;

        double scaledWidth = totalWidth * widthRatio;
        double scaledHeight = maxHeight * heightRatio;

        System.out.println("  原始宽度: " + totalWidth + ", 缩放后: " + scaledWidth);
        System.out.println("  原始高度: " + maxHeight + ", 缩放后: " + scaledHeight);
        System.out.println("  宽度误差: " + Math.abs(scaledWidth - expectedWidth));
        System.out.println("  高度误差: " + Math.abs(scaledHeight - expectedHeight));
        System.out.println("  缩放比例: W=" + String.format("%.6f", widthRatio) + ", H=" + String.format("%.6f", heightRatio));
        System.out.println();
    }

    private static void testDifferentHeightCalculations(Font baseFont, String text1, double size1,
                                                       String text2, double size2, double italicDegree) {
        FontRenderContext frc = new FontRenderContext(null, true, true);

        Font font2 = baseFont.deriveFont((float) size2); // 使用较大的字号
        AffineTransform transform = AffineTransform.getShearInstance(-Math.tan(Math.toRadians(italicDegree)), 0);
        Font italicFont = font2.deriveFont(transform);

        TextLayout layout = new TextLayout(text2, italicFont, frc);
        LineMetrics metrics = italicFont.getLineMetrics(text2, frc);
        Rectangle2D bounds = layout.getBounds();

        System.out.println("不同高度计算方法的对比（使用\"" + text2 + "\", size=" + size2 + "）:");
        System.out.println("  1. Ascent + Descent: " + (layout.getAscent() + layout.getDescent()));
        System.out.println("  2. Ascent + Descent + Leading: " + (layout.getAscent() + layout.getDescent() + layout.getLeading()));
        System.out.println("  3. Bounds Height: " + bounds.getHeight());
        System.out.println("  4. LineMetrics Height: " + metrics.getHeight());
        System.out.println("  5. Font Metrics: " + italicFont.getStringBounds(text2, frc).getHeight());
        System.out.println();
    }

    private static void testSegmentSpacing(Font baseFont, String text1, double size1,
                                          String text2, double size2, double italicDegree) {
        FontRenderContext frc = new FontRenderContext(null, true, true);

        System.out.println("测试段落间可能的额外间距:");

        // 计算基础宽度（无间距）
        Font font1 = baseFont.deriveFont((float) size1);
        AffineTransform transform1 = AffineTransform.getShearInstance(-Math.tan(Math.toRadians(italicDegree)), 0);
        Font italicFont1 = font1.deriveFont(transform1);
        TextLayout layout1 = new TextLayout(text1, italicFont1, frc);
        double width1 = layout1.getBounds().getWidth();

        Font font2 = baseFont.deriveFont((float) size2);
        AffineTransform transform2 = AffineTransform.getShearInstance(-Math.tan(Math.toRadians(italicDegree)), 0);
        Font italicFont2 = font2.deriveFont(transform2);
        TextLayout layout2 = new TextLayout(text2, italicFont2, frc);
        double width2 = layout2.getBounds().getWidth();

        double baseWidth = width1 + width2;
        double expectedWidth = 700.5253295898438;
        double widthRatio = expectedWidth / baseWidth;

        System.out.println("  基础宽度（无间距）: " + baseWidth);
        System.out.println("  缩放比例: " + widthRatio);

        // 测试不同的段落间距
        for (double spacing = 0; spacing <= 10; spacing += 0.5) {
            double totalWidth = width1 + spacing + width2;
            double scaledWidth = totalWidth * widthRatio;
            double error = Math.abs(scaledWidth - expectedWidth);
            if (error < 1.0) {
                System.out.println("  段落间距=" + spacing + " -> 缩放后宽度=" + scaledWidth + ", 误差=" + error);
            }
        }
        System.out.println();
    }
}
