package com.duoec.video.builder;

import com.duoec.base.core.util.JsonUtils;
import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.video.project.VideoProject;
import com.duoec.video.project.material.BaseTextMaterial;
import org.junit.jupiter.api.Test;

import java.util.List;

class ProjectBuilderTest {
    @Test
    void createBuilder() {
        VideoProject project = ProjectBuilder.createProject(SnowflakeIdUtils.nextTmpId(), "测试", 1080, 1920)
                .setTest(true) // 设置为测试模式
                .getScriptBuilder(0) // 进入第一个分镜

                .addTextTemplateAndGetBuilder(270464050694389761L, "太好了", 0, 3000) // 添加一个文本模板
                .setPosition(0, -400) // 指定展示位置，0，0表示视频中央 上正下负 左负右正
                .back() // 返回到 ProjectScriptBuilder

                .addVideoAndGetBuilder(535010997887571046L, "https://api.duoec.com/public/video/535010997887571046.mov", 0, 3000)
                .setLut("https://api.duoec.com/public/jianying/lut/C0307.cube", 90, null)
                .back()

                .addVideoAndGetBuilder(535010997887571021L, "https://api.duoec.com/public/video/535010997887571021.mp4", 3000, 3000)
                .addGreenBackgroundAndGetBuilder(535010997887571022L, "https://api.duoec.com/public/greenScreen/d8a0e31b50166b6219b1df1dbb90e284.png")
                .setChroma("#508921ff", 60, 20, 30)
                .back() // 返回到 ProjectVideoBuilder

                .back()// 返回到 ProjectScriptBuilder
                .back()// 返回到 ProjectBuilder
                .getProject(); // 导出工程

        System.out.println(JsonUtils.toJsonString(project));
    }

    @Test
    void createBuilder2() {
        VideoProject project = ProjectBuilder.createProject(SnowflakeIdUtils.nextTmpId(), "测试", 1080, 1920)
                .build(projectBuilder -> {
                    // ProjectVideo 上下文，可以在这里修改 ProjectVideo 信息
                    // projectBuilder.setTest(true);
                })
                .setTest(true)
                .buildGlobalStyle(296653948753219540L, new BaseTextMaterial.TextStyle(), styleBuilder -> {
                    // 进入 ProjectTextStyleBuilder 上下文，在这里可以编辑当前预设样式
                })
                .buildScript(0, scriptBuilder -> {
                    // 在第一个分镜下
                    // scriptBuilder.getVideoBuilder().setUpend(true); //虽然在分镜的上下文环境里也可以获得上层的builder，但不建议在此修改、处理分镜外的数据！！
                    scriptBuilder
                            .build(videoScriptBuilder -> {
                                // ProjectVideoScript 上下文，可以在这里修改 ProjectVideoScript 信息（当然，看上去有点多此一举，看你喜欢）
                                // videoScriptBuilder.setTime(0L, 3000L);
                            })
                            .buildNewVideo(535010997887571046L, "https://api.duoec.com/public/video/535010997887571046.mov", 0, 3000, videoBuilder -> {
                                // 进入 ProjectVideoBuilder，可以在这里修改 ProjectVideo 的信息
                                videoBuilder
                                        // .setPosition(0, -400) // 指定展示位置，0，0表示视频中央 上正下负 左负右正
                                        .setSpeed(50) // x0.5倍速播放
                                ;
                            }) // 添加一个视频
                            .buildNewTextTemplate(270464050694389761L, List.of("太好了"), 0, 3000, textTemplateBuilder -> {
                                // 进入 ProjectScriptBuilder 上下文。在这里可以处理当前新添加的文本模板信息
                            })
                            .buildNewSticker(270402997699280897L, 1500L, 3000L, stickerBuilder -> {
                                // 进入 ProjectStickerBuilder 上下文。这里可以处理当前新添加的贴纸信息
                            })
                            .builderNewVideoEffect(270464037793497089L, 5000L, 3000L, videoEffectBuilder -> {
                                // 进入 ProjectVideoEffectBuilder 上下文。这里可以处理当前新添加的 画面特效 信息
                            })
                            .buildNewFaceEffect(270464033541718017L, 1500L, 1000L, faceEffectBuilder -> {
                                // 进入 ProjectFaceEffectBuilder 上下文。这里可以处理当前新添加的 脸部特效 信息
                            })
                            .buildNewSound(270464042140893185L, 1000L, 3000L, soundBuilder -> {
                                // 进入 ProjectSoundBuilder 上下文，这里可以处理当前新添加的 特效音 信息
                            })
                            .buildNewText("你真好呀", 2001L, 999L, textBuilder -> {
                                // 进入 ProjectTextBuilder 上下文，这里可以处理当前新添加的 文本 信息
                                textBuilder
                                        .setPosition(0, -800)
                                        .setStyle(
                                                new BaseTextMaterial.TextStyle()
                                                        .setFontSize(14)
                                                        .setFillColor("#FF0000")
                                        )
                                ;
                            })
                    ;
                })
                .getProject();

        System.out.println(JsonUtils.toJsonString(project));
    }
}