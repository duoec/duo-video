package com.duoec.video.jy;

import com.duoec.base.core.util.FileUtils;
import com.duoec.base.core.util.JsonUtils;
import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.video.builder.ProjectBuilder;
import com.duoec.video.jy.dto.info.JianYingProjectInfo;
import com.duoec.video.jy.utils.JianyingResourceUtils;
import com.duoec.video.project.VideoProject;
import com.duoec.video.project.material.BaseTextMaterial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class JianyingBuilderTest extends BaseTest {
    private final JianyingBuilder jianyingBuilder = new JianyingBuilder();

    @Test
    void buildWithProjectJson() {
        VideoProject videoProject = FileUtils.readJson("001_base_project.json", VideoProject.class);
        videoProject.setTest(true);

        JianYingProjectInfo jyProject = jianyingBuilder.build(videoProject);

        Assertions.assertNotNull(jyProject);
    }

    @Test
    void buildWithProjectBuilder() {
        long textTemplateResourceId = 270464050694389761L;

        VideoProject videoProject = ProjectBuilder.createProject(SnowflakeIdUtils.nextTmpId(), "测试", 1080, 1920)
                .setTest(true) // 设置为测试模式

                .getScriptBuilder(0) // 进入第一个分镜
                .addTextTemplateAndGetBuilder(textTemplateResourceId, "太好了", 0, 3000) // 添加一个文本模板
                .setPosition(0, -400) // 指定展示位置，0，0表示视频中央 上正下负 左负右正
                .back() // 返回到 ProjectScriptBuilder

                .addTextAndGetBuilder(String.valueOf(textTemplateResourceId), 0, 3000)
                .setStyle(
                        new BaseTextMaterial.TextStyle()
                                .setFontSize(5)
                                .setTextAlign(1)
                                .setFillColor(JianyingResourceUtils.DEFAULT_FILL_COLOR)
                                .setFontName(JianyingResourceUtils.DEFAULT_FONT_NAME)
                )
                .setPosition(0, 1866)
                .back()// 返回到 ProjectScriptBuilder

                .back()// 返回到 ProjectBuilder
                .getProject(); // 导出工程

        JianYingProjectInfo jyProject = jianyingBuilder.build(videoProject);

        Assertions.assertNotNull(jyProject);
    }

    @Test
    public void buildWithBuilder() {
        VideoProject videoProject = ProjectBuilder.createProject(SnowflakeIdUtils.nextTmpId(), "002_duo_video", 1080, 1920)
                .build(projectBuilder -> {
                    // ProjectVideo 上下文，可以在这里修改 ProjectVideo 信息
//                    projectBuilder.setTest(true);
                })
                .setTest(true)
                .buildGlobalStyle(296653948753219540L, new BaseTextMaterial.TextStyle(), styleBuilder -> {
                    // 进入 ProjectTextStyleBuilder 上下文，在这里可以编辑当前预设样式
                    styleBuilder
                            .getGlobalStyleBuilder(textStyleBuilder -> {
                                textStyleBuilder
                                        .setFontSize(28)
                                        .setBold(true)
                                        .setItalic(true)
                                        .setTextAlign(1)
                                        .setFontName("抖音美好体")
                                        .setFillColor("#FFFF00")
                                        .setStrokeColor("#FF0000")
                                        .setStrokeWidth(10);
                            })
                            .setGlobalKeywordStyle(true)
                    ;
                })
                .buildScript(0, scriptBuilder -> {
                    // 在第一个分镜下
                    // scriptBuilder.getVideoBuilder().setUpend(true); //虽然在分镜的上下文环境里也可以获得上层的builder，但不建议在此修改、处理分镜外的数据！！
                    scriptBuilder
                            .buildNewImage(535010997887571096L, "https://api.duoec.com/public/image/535010997887571096.png", 3500L, 3000L, imageBuilder -> {
                                imageBuilder
                                        .setLayoutIndex(1000)
                                        .setZoom(7500, 7500)
                                        .setPosition(0, -1512)
                                        .setRotate(-90)
                                        .setVisible(true)
                                        .setHorizontal(true)
                                        .setVertical(true)
                                ;
                            })
                            .buildNewVideo(535010997887571021L, "https://api.duoec.com/public/video/535010997887571021.mp4", 0L, 3000L, videoBuilder -> {
                                videoBuilder
                                        .buildGreenScreen(535010997887571022L, "https://api.duoec.com/public/greenScreen/d8a0e31b50166b6219b1df1dbb90e284.png", backgroundBuilder -> {
                                            backgroundBuilder.setChroma("#4e8a1fff", 20, 10, 10);
                                        })
                                        .setMaterialStart(5000L)
                                        .setLayoutIndex(1000)
                                        .addTransition(270404457990455297L, 1000L)
                                        .setSpeed(100)
                                        .setZoom(10000, 10000)
                                        .setRotate(90)
                                        .setVisible(true)
                                        .setHorizontal(true)
                                        .setVolume(0)
                                ;
                            })
                            .buildNewVideo(535010997887571021L, "https://api.duoec.com/public/video/535010997887571021.mp4", 4000L, 3000L, videoBuilder -> {
                                videoBuilder
                                        .buildGreenScreen(535010997887571022L, "https://api.duoec.com/public/greenScreen/d8a0e31b50166b6219b1df1dbb90e284.png", backgroundBuilder -> {
                                            backgroundBuilder.setChroma("#4e8a1fff", 20, 10, 10);
                                        })
                                        .setMaterialStart(9000L)
                                        .setLayoutIndex(1000)
                                        .addMask(270415264124764161L, maskBuilder -> {
                                            maskBuilder
                                                    .setFeather(5)
                                                    .setRotation(90)
                                                    .setWidth(0.5)
                                                    .setHeight(0.28)
                                                    .setCenterX(0.07)
                                                    .setCenterY(0.25)
                                                    .setPointX(400)
                                                    .setPointY(400)
                                            ;
                                        })
                                        .setSpeed(100)
                                        .setZoom(10000, 10000)
                                        .setRotate(90)
                                        .setVisible(true)
                                        .setHorizontal(true)
                                        .setVolume(0)
                                ;
                            })
                            .buildNewAudio(535010997887571025L, "https://api.duoec.com/public/audio/535010997887571025.mp3", 0L, 8000L, audioBuilder -> {
                                audioBuilder
                                        .setMaterialTime(170, 126869)
                                        .setMaterialStart(10000L)
                                        .setLayoutIndex(1000)
                                        .setSpeed(100)
                                        .setVisible(true)
                                        .setVolume(-50)
                                ;
                            })
                            .builderNewVideoEffect(270464037793497089L, 5000L, 3000L, videoEffectBuilder -> {

                            })
                            .buildNewFaceEffect(270464033541718017L, 1500L, 1000L, faceEffectBuilder -> {

                            })
                            .buildNewVideo(535010997887571046L, "https://api.duoec.com/public/video/535010997887571046.mov", 3000L, 5000L, videoBuilder -> {
                                videoBuilder
                                        .setMaterialTime(0L, 14264L)
                                        .setMaterialStart(1000L)
                                        .setLayoutIndex(1000)
                                        .addTransition(270404457990455297L, 1000L)
                                        .setSpeed(200)
                                        .setZoom(10000, 10000)
                                        .setRotate(90)
                                        .setVisible(true)
                                        .setHorizontal(true)
                                        .setVolume(-100)
                                ;
                            })
                            .buildNewText("测试中文字幕", 10L, 1990L, textBuilder -> {
                                textBuilder.setLayoutIndex(1000)
                                        .setPosition(0, -1000)
                                        .setRotate(0)
                                        .setAsSubtitle(true)
                                        .setStyle(
                                                new BaseTextMaterial.TextStyle()
                                                        .setFontSize(14)
                                                        .setBold(false)
                                                        .setItalic(false)
                                                        .setTextAlign(1)
                                                        .setFontName("微软雅黑")
                                                        .setFillColor("#FFFFFF")
                                                        .setStrokeColor("#FF0000")
                                                        .setStrokeWidth(10)
                                        )
                                        .addWord(2, 2, wordTextBuilder -> {
                                            wordTextBuilder
                                                    .setFontSize(16)
                                                    .setFillColor("#00FFFF")
                                                    .setStrokeWidth(20)
                                                    .setStrokeColor("#0000FF")
                                            ;
                                        })
                                        .addWord(3, 2, wordTextBuilder -> {
                                            wordTextBuilder
                                                    .setStyleId(296653948753219540L)
                                                    .setFontSize(18)
                                            ;
                                        })
                                ;
                            })
                            .buildNewText("你真好呀", 2001L, 999L, textBuilder -> {
                                textBuilder
                                        .setLayoutIndex(1000)
                                        .setPosition(0, -800)
                                        .setRotate(0)
                                        .setAsSubtitle(true)
                                        .setStyleId(296653948753219540L)
                                        .addWord(1, 2, textWordBuilder -> {
                                            textWordBuilder.setFlowerId(270413717936603137L);
                                        })
                                ;
                            })
                            .buildNewTextTemplate(270414005699805185L, List.of("非", "常", "棒", "duoec.com"), 2001L, 999L, textBuilder -> {
                                textBuilder
                                        .setLayoutIndex(1000)
                                        .setZoom(5000, 5000)
                                        .setPosition(0, 1400)
                                ;
                            })
                            .buildNewSound(270464042140893185L, 1000L, 3000L, soundBuilder -> {

                            })
                            .buildNewSticker(270402997699280897L, 1500L, 3000L, stickerBuilder -> {
                                stickerBuilder
                                        .setZoom(5000, 5000)
                                        .setPosition(500, 0)
                                        .setRotate(-45)
                                ;
                            })
                    ;
                })
                .getProject();
        System.out.println(JsonUtils.toJsonString(videoProject));
        JianYingProjectInfo jyProject = jianyingBuilder.build(videoProject);
    }

    @Test
    void buildWithKeyframes() {
        // 测试关键帧功能
        long videoId = 535010997887571046L;
        String videoUrl = "https://api.duoec.com/public/video/535010997887571046.mov";

        VideoProject videoProject = ProjectBuilder.createProject(SnowflakeIdUtils.nextTmpId(), "关键帧测试", 1280, 720)
                .setTest(true)
                .buildScript(0, scriptBuilder -> {
                    scriptBuilder.buildNewVideo(videoId, videoUrl, 0, 5000, videoBuilder -> {
                        // 添加位置关键帧动画：模拟草稿中的位移效果
                        videoBuilder.getKeyframeBuilder()
                                // 733ms 时在初始位置 (0, 0)
                                .addPositionKeyframe(733, 0.0, 0.0)
                                // 3133ms 时移动到 (200, -330)
                                .addPositionKeyframe(3133, 200.0, -330.0)
                                .apply();
                    });
                })
                .getProject();

        JianYingProjectInfo jyProject = jianyingBuilder.build(videoProject);

        Assertions.assertNotNull(jyProject);

        // 验证关键帧输出格式
        var videoTrack = jyProject.getTracks().stream()
                .filter(t -> "video".equals(t.getType()))
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(videoTrack, "应有视频轨道");
        Assertions.assertFalse(videoTrack.getSegments().isEmpty(), "应有视频片段");

        var segment = videoTrack.getSegments().get(0);
        var commonKeyframes = segment.getCommonKeyframes();
        Assertions.assertNotNull(commonKeyframes, "应有关键帧");
        // position 应拆分为 X 和 Y 两个轨道
        Assertions.assertEquals(2, commonKeyframes.size(), "位置关键帧应拆分为 X/Y 两个轨道");

        // 验证 KFTypePositionX
        var kfX = commonKeyframes.stream()
                .filter(kf -> "KFTypePositionX".equals(kf.getPropertyType()))
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(kfX, "应有 KFTypePositionX 轨道");
        Assertions.assertEquals("", kfX.getMaterialId(), "materialId 应为空字符串");
        Assertions.assertEquals(2, kfX.getKeyframeList().size());

        // 验证时间偏移（毫秒转微秒）
        var itemX0 = kfX.getKeyframeList().get(0);
        Assertions.assertEquals(733000L, itemX0.getTimeOffset(), "timeOffset 应为微秒");
        // 验证值（像素转归一化：0 / 1280 = 0）
        Assertions.assertEquals(0.0, itemX0.getValues().get(0), 0.0001);
        // 验证控制点默认 (0, 0)
        Assertions.assertEquals(0.0, itemX0.getLeftControl().getX());
        Assertions.assertEquals(0.0, itemX0.getLeftControl().getY());

        var itemX1 = kfX.getKeyframeList().get(1);
        Assertions.assertEquals(3133000L, itemX1.getTimeOffset());
        // 200 / 1280 = 0.15625
        Assertions.assertEquals(0.15625, itemX1.getValues().get(0), 0.0001);

        // 验证 KFTypePositionY
        var kfY = commonKeyframes.stream()
                .filter(kf -> "KFTypePositionY".equals(kf.getPropertyType()))
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(kfY, "应有 KFTypePositionY 轨道");
        var itemY1 = kfY.getKeyframeList().get(1);
        // -330 / 720 = -0.4583333...
        Assertions.assertEquals(-0.4583333, itemY1.getValues().get(0), 0.0001);
    }

    @Test
    void buildWithTextColorKeyframes() {
        // 测试文字颜色关键帧（模拟草稿中的 KFTypeTextColor）
        VideoProject videoProject = ProjectBuilder.createProject(SnowflakeIdUtils.nextTmpId(), "文字颜色关键帧测试", 1280, 720)
                .setTest(true)
                .buildScript(0, scriptBuilder -> {
                    scriptBuilder.buildNewText("默认文本", 0, 3333, textBuilder -> {
                        textBuilder.getKeyframeBuilder()
                                // 1300ms 黄色
                                .addTextColorKeyframe(1300, "#FFDE00")
                                // 1333ms 黄色（保持）
                                .addTextColorKeyframe(1333, "#FFDE00")
                                // 2766ms 红色
                                .addTextColorKeyframe(2766, "#FF0000")
                                .apply();
                    });
                })
                .getProject();

        JianYingProjectInfo jyProject = jianyingBuilder.build(videoProject);

        Assertions.assertNotNull(jyProject);

        // 验证文字颜色关键帧
        var textTrack = jyProject.getTracks().stream()
                .filter(t -> "text".equals(t.getType()))
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(textTrack, "应有文字轨道");

        var segment = textTrack.getSegments().get(0);
        var commonKeyframes = segment.getCommonKeyframes();
        Assertions.assertNotNull(commonKeyframes, "应有关键帧");
        Assertions.assertEquals(1, commonKeyframes.size(), "文字颜色应为单个轨道");

        var kfColor = commonKeyframes.get(0);
        Assertions.assertEquals("KFTypeTextColor", kfColor.getPropertyType());
        Assertions.assertEquals("", kfColor.getMaterialId());
        Assertions.assertEquals(3, kfColor.getKeyframeList().size());

        // 验证第一个关键帧（黄色 #FFDE00 → [1.0, 0.8706, 0.0, 1.0]）
        var colorItem0 = kfColor.getKeyframeList().get(0);
        Assertions.assertEquals(1300000L, colorItem0.getTimeOffset());
        Assertions.assertEquals(4, colorItem0.getValues().size(), "颜色值应为 RGBA 4个分量");
        Assertions.assertEquals(1.0, colorItem0.getValues().get(0), 0.001); // R
        Assertions.assertEquals(0.8706, colorItem0.getValues().get(1), 0.001); // G
        Assertions.assertEquals(0.0, colorItem0.getValues().get(2), 0.001); // B
        Assertions.assertEquals(1.0, colorItem0.getValues().get(3), 0.001); // A

        // 验证最后一个关键帧（红色 #FF0000 → [1.0, 0.0, 0.0, 1.0]）
        var colorItem2 = kfColor.getKeyframeList().get(2);
        Assertions.assertEquals(2766000L, colorItem2.getTimeOffset());
        Assertions.assertEquals(1.0, colorItem2.getValues().get(0), 0.001); // R
        Assertions.assertEquals(0.0, colorItem2.getValues().get(1), 0.001); // G
        Assertions.assertEquals(0.0, colorItem2.getValues().get(2), 0.001); // B
    }
}
