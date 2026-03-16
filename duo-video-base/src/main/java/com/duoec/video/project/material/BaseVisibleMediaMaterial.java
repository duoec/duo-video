package com.duoec.video.project.material;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 基础的可视多媒体素材，包含：视频、图片
 */
@Data
public class BaseVisibleMediaMaterial extends BaseMaterial {
    /**
     * 媒体ID，本地缓存会使用此ID！
     */
    private Long mediaId;

    /**
     * 宽 （无需设置，在创作时，会重新检测）
     */
    private Integer width;

    /**
     * 高（无需设置，在创作时，会重新检测）
     */
    private Integer height;

    /**
     * Lut文件
     */
    private Lut lut;

    /**
     * 绿幕背景（不一定是绿色，也可以是蓝色、白色...）
     * 绿幕背景会做以下处理：
     * 1. 将当前素材使用色度抠像，去除绿幕
     * 2. 将指定的背景素材放置于底部
     * 3. 制作成复合片段，追加到当前视频
     */
    private GreenScreen greenScreen;

    /**
     * 背景模糊强度，越强越模糊，取值：[1, 5]，默认：3
     * 仅在非标准尺寸素材时才有效。比如当前项目尺寸为 1080 x 1920，而当前尺寸为 540 x 960（可以通过缩放达到标准尺寸），这时仍不会处理
     * 但如果当前素材尺寸比例不为 1080 / 1920时，会进行如下处理：
     * 1. 将当前素材复制一份，作为背景，固定比例拉伸，至最小边与当前视频匹配
     * 2. 在此背景素材上面叠加 {blurredBackgroundFill} 个画面模糊特效（可以通过叠加数量控制模糊程度）
     * 3. 最后叠加上当前素材，等比例拉伸到最大边与当前视频匹配
     * 4. 将这几部分片段抽取成复合片段，追加到当前视频
     */
    private Integer blurredBackgroundFill;

    @Override
    public String getType() {
        return MaterialTypeEnum.MATERIAL_TYPE_VIDEO;
    }

    @Data
    @Accessors(chain = true)
    public static class Lut {
        /**
         * LUT文件地址
         */
        private String url;

        /**
         * 强度 [0, 100]
         */
        private Integer strength;

        /**
         * 肤色保护 [0, 100]
         */
        private Integer skinToneCorrection;
    }

    @Data
    @Accessors(chain = true)
    public static class GreenScreen {
        /**
         * 背景素材媒体ID，支持视频、图片
         */
        private Long mediaId;

        /**
         * 背景基础颜色
         */
        private String color;

        /**
         * 强度
         */
        private Integer strength;

        /**
         * 边缘羽化
         */
        private Integer edgeFeather;

        /**
         * 边缘清理
         */
        private Integer edgeCleanup;
    }
}
