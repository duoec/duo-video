package com.duoec.video.jy.utils;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.File;

/**
 * 矩形尺寸计算诊断工具
 *
 * 使用方法：
 * 1. 替换下面的参数为你实际遇到误差的情况
 * 2. 运行此工具查看详细的计算过程
 * 3. 根据输出的建议调整 calculateTextTemplateRectangle 方法
 */
public class RectangleDiagnosticTool {

    public static void main(String[] args) throws Exception {
        // ============= 配置区域 - 替换为你的实际数据 =============

        String fontPath = "/Users/xuwenzhen/Movies/JianyingPro/User Data/Cache/effect/7070430593925779981/ced7a98a9bcc8984318a2c215192b0c5/楚辰体.ttf";

        // 原始文本段落配置（从richText解析）
        String[] texts = {"前方", "高能"};
        double[] sizes = {39.266, 44.266};
        boolean[] italics = {true, true};

        // 新文本（要计算的）
        String newText = "前方高能";

        // 文本参数
        double italicDegree = 10;
        double letterSpacing = 0;
        double lineSpacing = 0.02;
        double boldValue = 0.008;

        // 期望的尺寸（从original_size获取）
        double expectedWidth = 700.5253295898438;
        double expectedHeight = 258.21832275390625;

        // ============= 诊断开始 =============

        File fontFile = new File(fontPath);
        if (!fontFile.exists()) {
            System.out.println("❌ 字体文件不存在: " + fontPath);
            return;
        }

        Font baseFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        FontRenderContext frc = new FontRenderContext(null, true, true);

        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║         剪映文本模板尺寸计算诊断工具                           ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝\n");

        // 1. 计算原始文本尺寸（用于获取缩放系数）
        System.out.println("📊 步骤1: 计算原始文本的Font渲染尺寸");
        System.out.println("─────────────────────────────────────────────────────────────");

        double originalRawWidth = 0;
        double originalRawHeight = 0;

        for (int i = 0; i < texts.length; i++) {
            Font font = baseFont.deriveFont((float) sizes[i]);
            if (boldValue > 0) {
                font = font.deriveFont(Font.BOLD);
            }

            double segmentWidth, segmentHeight;

            if (italics[i] && italicDegree > 0) {
                AffineTransform transform = AffineTransform.getShearInstance(-Math.tan(Math.toRadians(italicDegree)), 0);
                Font italicFont = font.deriveFont(transform);
                TextLayout layout = new TextLayout(texts[i], italicFont, frc);
                Rectangle2D bounds = layout.getBounds();
                segmentWidth = bounds.getWidth();
                segmentHeight = layout.getAscent() + layout.getDescent();
            } else {
                Rectangle2D bounds = font.getStringBounds(texts[i], frc);
                segmentWidth = bounds.getWidth();
                segmentHeight = bounds.getHeight();
            }

            if (texts[i].length() > 1) {
                segmentWidth += (texts[i].length() - 1) * letterSpacing * sizes[i];
            }

            System.out.printf("  段落%d: \"%s\" (size=%.3f, italic=%s)\n", i + 1, texts[i], sizes[i], italics[i]);
            System.out.printf("    → 宽度: %.4f, 高度: %.4f\n", segmentWidth, segmentHeight);

            originalRawWidth += segmentWidth;
            originalRawHeight = Math.max(originalRawHeight, segmentHeight);
        }

        System.out.printf("\n  总计: 宽度=%.4f, 高度=%.4f\n\n", originalRawWidth, originalRawHeight);

        // 2. 计算缩放系数
        System.out.println("🔍 步骤2: 计算缩放系数");
        System.out.println("─────────────────────────────────────────────────────────────");

        double scaleFactorWidth = expectedWidth / originalRawWidth;
        double scaleFactorHeight = expectedHeight / originalRawHeight;

        System.out.printf("  期望尺寸: %.4f x %.4f\n", expectedWidth, expectedHeight);
        System.out.printf("  Font计算: %.4f x %.4f\n", originalRawWidth, originalRawHeight);
        System.out.printf("  缩放系数: %.6f x %.6f\n\n", scaleFactorWidth, scaleFactorHeight);

        // 3. 计算新文本
        System.out.println("✏️  步骤3: 计算新文本 \"" + newText + "\" 的尺寸");
        System.out.println("─────────────────────────────────────────────────────────────");

        // 这里简化处理，假设按段落数量平均分配
        String[] newTexts = splitText(newText, texts.length);

        double totalWidth = 0;
        double maxHeight = 0;

        for (int i = 0; i < Math.min(newTexts.length, texts.length); i++) {
            if (newTexts[i].isEmpty()) continue;

            Font font = baseFont.deriveFont((float) sizes[i]);
            if (boldValue > 0) {
                font = font.deriveFont(Font.BOLD);
            }

            double segmentWidth, segmentHeight;

            if (italics[i] && italicDegree > 0) {
                AffineTransform transform = AffineTransform.getShearInstance(-Math.tan(Math.toRadians(italicDegree)), 0);
                Font italicFont = font.deriveFont(transform);
                TextLayout layout = new TextLayout(newTexts[i], italicFont, frc);
                Rectangle2D bounds = layout.getBounds();
                segmentWidth = bounds.getWidth();
                segmentHeight = layout.getAscent() + layout.getDescent();
            } else {
                Rectangle2D bounds = font.getStringBounds(newTexts[i], frc);
                segmentWidth = bounds.getWidth();
                segmentHeight = bounds.getHeight();
            }

            if (newTexts[i].length() > 1) {
                segmentWidth += (newTexts[i].length() - 1) * letterSpacing * sizes[i];
            }

            System.out.printf("  段落%d: \"%s\" → 宽度: %.4f, 高度: %.4f\n", i + 1, newTexts[i], segmentWidth, segmentHeight);

            totalWidth += segmentWidth;
            maxHeight = Math.max(maxHeight, segmentHeight);
        }

        System.out.printf("\n  总计: 宽度=%.4f, 高度=%.4f\n\n", totalWidth, maxHeight);

        // 4. 应用缩放系数
        System.out.println("📐 步骤4: 应用缩放系数");
        System.out.println("─────────────────────────────────────────────────────────────");

        double scaledWidth = totalWidth * scaleFactorWidth;
        double scaledHeight = maxHeight * scaleFactorHeight;

        System.out.printf("  缩放前: %.4f x %.4f\n", totalWidth, maxHeight);
        System.out.printf("  缩放后: %.4f x %.4f\n\n", scaledWidth, scaledHeight);

        // 5. 测试不同的取整方式
        System.out.println("🎯 步骤5: 测试不同的取整方式");
        System.out.println("─────────────────────────────────────────────────────────────");

        int roundedWidth = (int) Math.round(scaledWidth);
        int roundedHeight = (int) Math.round(scaledHeight);
        int ceilWidth = (int) Math.ceil(scaledWidth);
        int ceilHeight = (int) Math.ceil(scaledHeight);
        int floorWidth = (int) Math.floor(scaledWidth);
        int floorHeight = (int) Math.floor(scaledHeight);

        double errorRound = Math.abs(roundedWidth - expectedWidth) + Math.abs(roundedHeight - expectedHeight);
        double errorCeil = Math.abs(ceilWidth - expectedWidth) + Math.abs(ceilHeight - expectedHeight);
        double errorFloor = Math.abs(floorWidth - expectedWidth) + Math.abs(floorHeight - expectedHeight);

        System.out.printf("  四舍五入 (round): %d x %d, 总误差: %.2f\n", roundedWidth, roundedHeight, errorRound);
        System.out.printf("  向上取整 (ceil):  %d x %d, 总误差: %.2f\n", ceilWidth, ceilHeight, errorCeil);
        System.out.printf("  向下取整 (floor): %d x %d, 总误差: %.2f\n\n", floorWidth, floorHeight, errorFloor);

        // 6. 结论和建议
        System.out.println("💡 诊断结论");
        System.out.println("═════════════════════════════════════════════════════════════");

        if (errorRound < 0.001) {
            System.out.println("✅ 计算精度非常高，误差在浮点精度范围内！");
            System.out.println("   当前的实现已经是最优的。");
        } else if (errorRound < 1.0) {
            System.out.println("⚠️  检测到小于1像素的误差");
            System.out.println("   可能原因：");
            System.out.println("   1. 取整方式：尝试使用 ceil 或 floor");
            if (errorCeil < errorRound) {
                System.out.println("   → 建议：改用 Math.ceil() 向上取整");
            } else if (errorFloor < errorRound) {
                System.out.println("   → 建议：改用 Math.floor() 向下取整");
            }
            System.out.println("   2. 段落间可能有微小间距");
            System.out.println("   3. 可能需要包含 leading (行间距)");
        } else {
            System.out.println("⚠️  检测到较大误差");
            System.out.println("   可能原因：");
            System.out.println("   1. 字体文件不匹配");
            System.out.println("   2. richText 解析错误");
            System.out.println("   3. 文本参数配置不正确");
            System.out.println("   4. 需要考虑额外的边距或padding");
            System.out.println("\n   建议：检查 text_params 的所有参数是否正确解析");
        }

        System.out.println("═════════════════════════════════════════════════════════════\n");
    }

    private static String[] splitText(String text, int segments) {
        String[] result = new String[segments];
        int len = text.length();
        int segmentLen = len / segments;

        for (int i = 0; i < segments; i++) {
            int start = i * segmentLen;
            int end = (i == segments - 1) ? len : (i + 1) * segmentLen;
            result[i] = text.substring(start, Math.min(end, len));
        }

        return result;
    }
}
