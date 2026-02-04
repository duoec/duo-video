package com.duoec.video.controller;

import com.duoec.base.core.util.JsonUtils;
import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.base.dto.response.BaseResponse;
import com.duoec.video.dto.request.*;
import com.duoec.video.jy.JianyingBuilder;
import com.duoec.video.jy.JianyingProjectBuildState;
import com.duoec.video.jy.dto.info.JianYingProjectInfo;
import com.duoec.video.jy.service.impl.StorageServiceImpl;
import com.duoec.video.project.VideoProject;
import com.duoec.video.project.material.TextStyle;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 视频API控制器测试
 * 基于 JianyingBuilderTest#buildWithBuilder 实现完全相同的视频配置
 */
@SpringBootTest(classes = com.duoec.video.TestConfiguration.class)
@AutoConfigureMockMvc
class VideoProjectApiControllerTest {
    static {
        JianyingBuilder.storageService = new StorageServiceImpl();
        JianyingProjectBuildState.DEBUG_JY_DRAFT_DIR = "/Users/xuwenzhen/Movies/JianyingPro/User Data/Projects/com.lveditor.draft/";
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testBuildCompleteVideoProject() throws Exception {
        long projectId = SnowflakeIdUtils.nextTmpId();

        // 1. 创建项目
        CreateProjectRequest createProjectRequest = new CreateProjectRequest();
        createProjectRequest.setProjectId(projectId);
        createProjectRequest.setProjectName("003_duo_video");
        createProjectRequest.setWidth(1080);
        createProjectRequest.setHeight(1920);
        createProjectRequest.setTest(true); // 设置为测试模式

        MvcResult createResult = mockMvc.perform(post("/api/video")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJsonString(createProjectRequest)))
                .andExpect(status().isOk())
                .andReturn();

        BaseResponse<VideoProject> createResponse = JsonUtils.toObject(
                createResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );
        assertEquals(0, createResponse.getCode());
        assertNotNull(createResponse.getData());
        System.out.println("1. 创建项目成功");

        // 2. 设置全局样式
        SetGlobalStyleRequest globalStyleRequest = new SetGlobalStyleRequest();
        globalStyleRequest.setProjectId(projectId);
        globalStyleRequest.setStyleId(296653948753219540L);
        TextStyle textStyle = new TextStyle()
                .setFontSize(28)
                .setBold(true)
                .setItalic(true)
                .setTextAlign(1)
                .setFontName("抖音美好体")
                .setFillColor("#FFFF00")
                .setStrokeColor("#FF0000")
                .setStrokeWidth(10);
        globalStyleRequest.setTextStyle(textStyle);
        globalStyleRequest.setGlobalKeywordStyle(true);

        MvcResult styleResult = mockMvc.perform(post("/api/project/global-style")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJsonString(globalStyleRequest)))
                .andExpect(status().isOk())
                .andReturn();

        BaseResponse<VideoProject> styleResponse = JsonUtils.toObject(
                styleResult.getResponse().getContentAsString(),
                new TypeReference<>() {}
        );
        assertEquals(0, styleResponse.getCode());
        System.out.println("2. 设置全局样式成功");

        // 3. 添加图片素材
        AddImageRequest imageRequest = new AddImageRequest();
        imageRequest.setProjectId(projectId);
        imageRequest.setScriptIndex(0);
        imageRequest.setImageId(535010997887571096L);
        imageRequest.setImageUrl("https://api.duoec.com/public/image/535010997887571096.png");
        imageRequest.setStartTime(3500L);
        imageRequest.setDuration(3000L);
        imageRequest.setLayoutIndex(1000);
        imageRequest.setZoomX(7500);
        imageRequest.setZoomY(7500);
        imageRequest.setPositionX(0);
        imageRequest.setPositionY(-1512);
        imageRequest.setRotate(-90);
        imageRequest.setVisible(true);
        imageRequest.setHorizontal(true);
        imageRequest.setVertical(true);

        MvcResult imageResult = mockMvc.perform(post("/api/project/image")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJsonString(imageRequest)))
                .andExpect(status().isOk())
                .andReturn();

        BaseResponse<VideoProject> imageResponse = JsonUtils.toObject(
                imageResult.getResponse().getContentAsString(),
                new TypeReference<>() {}
        );
        assertEquals(0, imageResponse.getCode());
        System.out.println("3. 添加图片素材成功");

        // 4. 添加第一个视频（带绿幕背景和转场）
        AddVideoRequest video1Request = new AddVideoRequest();
        video1Request.setProjectId(projectId);
        video1Request.setScriptIndex(0);
        video1Request.setVideoId(535010997887571021L);
        video1Request.setVideoUrl("https://api.duoec.com/public/video/535010997887571021.mp4");
        video1Request.setStartTime(0L);
        video1Request.setDuration(3000L);
        video1Request.setMaterialStart(5000L);
        video1Request.setLayoutIndex(1000);
        video1Request.setSpeed(100);
        video1Request.setZoomX(10000);
        video1Request.setZoomY(10000);
        video1Request.setRotate(90);
        video1Request.setVisible(true);
        video1Request.setHorizontal(true);
        video1Request.setVolume(0);
        video1Request.setTransitionId(270404457990455297L);
        video1Request.setTransitionDuration(1000L);

        // 设置绿幕背景
        GreenBackgroundParam greenBackground1 = new GreenBackgroundParam();
        greenBackground1.setGreenScreenId(535010997887571022L);
        greenBackground1.setGreenScreenUrl("https://api.duoec.com/public/greenScreen/d8a0e31b50166b6219b1df1dbb90e284.png");
        greenBackground1.setChromaColor("#4e8a1fff");
        greenBackground1.setChromaStrength(20);
        greenBackground1.setChromaShadow(10);
        greenBackground1.setChromaHighlight(10);
        video1Request.setGreenBackground(greenBackground1);

        MvcResult video1Result = mockMvc.perform(post("/api/project/video")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJsonString(video1Request)))
                .andExpect(status().isOk())
                .andReturn();

        BaseResponse<VideoProject> video1Response = JsonUtils.toObject(
                video1Result.getResponse().getContentAsString(),
                new TypeReference<>() {}
        );
        assertEquals(0, video1Response.getCode());
        System.out.println("4. 添加第一个视频（带绿幕）成功");

        // 5. 添加第二个视频（带绿幕背景和蒙版）
        AddVideoRequest video2Request = new AddVideoRequest();
        video2Request.setProjectId(projectId);
        video2Request.setScriptIndex(0);
        video2Request.setVideoId(535010997887571021L);
        video2Request.setVideoUrl("https://api.duoec.com/public/video/535010997887571021.mp4");
        video2Request.setStartTime(4000L);
        video2Request.setDuration(3000L);
        video2Request.setMaterialStart(9000L);
        video2Request.setLayoutIndex(1000);
        video2Request.setSpeed(100);
        video2Request.setZoomX(10000);
        video2Request.setZoomY(10000);
        video2Request.setRotate(90);
        video2Request.setVisible(true);
        video2Request.setHorizontal(true);
        video2Request.setVolume(0);

        // 设置绿幕背景
        GreenBackgroundParam greenBackground2 = new GreenBackgroundParam();
        greenBackground2.setGreenScreenId(535010997887571022L);
        greenBackground2.setGreenScreenUrl("https://api.duoec.com/public/greenScreen/d8a0e31b50166b6219b1df1dbb90e284.png");
        greenBackground2.setChromaColor("#4e8a1fff");
        greenBackground2.setChromaStrength(20);
        greenBackground2.setChromaShadow(10);
        greenBackground2.setChromaHighlight(10);
        video2Request.setGreenBackground(greenBackground2);

        // 设置蒙版
        MaskParam mask = new MaskParam();
        mask.setMaskId(270415264124764161L);
        mask.setFeather(5);
        mask.setRotation(90);
        mask.setWidth(0.5);
        mask.setHeight(0.28);
        mask.setCenterX(0.07);
        mask.setCenterY(0.25);
        mask.setPointX(400);
        mask.setPointY(400);
        video2Request.setMask(mask);

        MvcResult video2Result = mockMvc.perform(post("/api/project/video")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJsonString(video2Request)))
                .andExpect(status().isOk())
                .andReturn();

        BaseResponse<VideoProject> video2Response = JsonUtils.toObject(
                video2Result.getResponse().getContentAsString(),
                new TypeReference<>() {}
        );
        assertEquals(0, video2Response.getCode());
        System.out.println("5. 添加第二个视频（带绿幕和蒙版）成功");

        // 6. 添加音频素材
        AddAudioRequest audioRequest = new AddAudioRequest();
        audioRequest.setProjectId(projectId);
        audioRequest.setScriptIndex(0);
        audioRequest.setAudioId(535010997887571025L);
        audioRequest.setAudioUrl("https://api.duoec.com/public/audio/535010997887571025.mp3");
        audioRequest.setStartTime(0L);
        audioRequest.setDuration(8000L);
        audioRequest.setMaterialTimeStart(170L);
        audioRequest.setMaterialTimeEnd(126869L);
        audioRequest.setMaterialStart(10000L);
        audioRequest.setLayoutIndex(1000);
        audioRequest.setSpeed(100);
        audioRequest.setVisible(true);
        audioRequest.setVolume(-50);

        MvcResult audioResult = mockMvc.perform(post("/api/project/audio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJsonString(audioRequest)))
                .andExpect(status().isOk())
                .andReturn();

        BaseResponse<VideoProject> audioResponse = JsonUtils.toObject(
                audioResult.getResponse().getContentAsString(),
                new TypeReference<>() {}
        );
        assertEquals(0, audioResponse.getCode());
        System.out.println("6. 添加音频素材成功");

        // 7. 添加视频特效
        AddVideoEffectRequest videoEffectRequest = new AddVideoEffectRequest();
        videoEffectRequest.setProjectId(projectId);
        videoEffectRequest.setScriptIndex(0);
        videoEffectRequest.setEffectId(270464037793497089L);
        videoEffectRequest.setStartTime(5000L);
        videoEffectRequest.setDuration(3000L);

        MvcResult videoEffectResult = mockMvc.perform(post("/api/project/video-effect")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJsonString(videoEffectRequest)))
                .andExpect(status().isOk())
                .andReturn();

        BaseResponse<VideoProject> videoEffectResponse = JsonUtils.toObject(
                videoEffectResult.getResponse().getContentAsString(),
                new TypeReference<>() {}
        );
        assertEquals(0, videoEffectResponse.getCode());
        System.out.println("7. 添加视频特效成功");

        // 8. 添加人脸特效
        AddFaceEffectRequest faceEffectRequest = new AddFaceEffectRequest();
        faceEffectRequest.setProjectId(projectId);
        faceEffectRequest.setScriptIndex(0);
        faceEffectRequest.setEffectId(270464033541718017L);
        faceEffectRequest.setStartTime(1500L);
        faceEffectRequest.setDuration(1000L);

        MvcResult faceEffectResult = mockMvc.perform(post("/api/project/face-effect")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJsonString(faceEffectRequest)))
                .andExpect(status().isOk())
                .andReturn();

        BaseResponse<VideoProject> faceEffectResponse = JsonUtils.toObject(
                faceEffectResult.getResponse().getContentAsString(),
                new TypeReference<>() {}
        );
        assertEquals(0, faceEffectResponse.getCode());
        System.out.println("8. 添加人脸特效成功");

        // 9. 添加第三个视频（带转场和素材时间范围）
        AddVideoRequest video3Request = new AddVideoRequest();
        video3Request.setProjectId(projectId);
        video3Request.setScriptIndex(0);
        video3Request.setVideoId(535010997887571046L);
        video3Request.setVideoUrl("https://api.duoec.com/public/video/535010997887571046.mov");
        video3Request.setStartTime(3000L);
        video3Request.setDuration(5000L);
        video3Request.setMaterialTimeStart(0L);
        video3Request.setMaterialTimeEnd(14264L);
        video3Request.setMaterialStart(1000L);
        video3Request.setLayoutIndex(1000);
        video3Request.setSpeed(200);
        video3Request.setZoomX(10000);
        video3Request.setZoomY(10000);
        video3Request.setRotate(90);
        video3Request.setVisible(true);
        video3Request.setHorizontal(true);
        video3Request.setVolume(-100);
        video3Request.setTransitionId(270404457990455297L);
        video3Request.setTransitionDuration(1000L);

        MvcResult video3Result = mockMvc.perform(post("/api/project/video")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJsonString(video3Request)))
                .andExpect(status().isOk())
                .andReturn();

        BaseResponse<VideoProject> video3Response = JsonUtils.toObject(
                video3Result.getResponse().getContentAsString(),
                new TypeReference<>() {}
        );
        assertEquals(0, video3Response.getCode());
        System.out.println("9. 添加第三个视频成功");

        // 10. 添加第一个文本（带样式和关键词样式）
        AddTextRequest text1Request = new AddTextRequest();
        text1Request.setProjectId(projectId);
        text1Request.setScriptIndex(0);
        text1Request.setText("测试中文字幕");
        text1Request.setStartTime(10L);
        text1Request.setDuration(1990L);
        text1Request.setLayoutIndex(1000);
        text1Request.setPositionX(0);
        text1Request.setPositionY(-1000);
        text1Request.setRotate(0);
        text1Request.setAsSubtitle(true);

        TextStyle text1Style = new TextStyle()
                .setFontSize(14)
                .setBold(false)
                .setItalic(false)
                .setTextAlign(1)
                .setFontName("微软雅黑")
                .setFillColor("#FFFFFF")
                .setStrokeColor("#FF0000")
                .setStrokeWidth(10);
        text1Request.setStyle(text1Style);

        // 添加关键词样式
        AddTextRequest.WordStyleRequest word1 = new AddTextRequest.WordStyleRequest();
        word1.setStartIndex(2);
        word1.setLength(2);
        word1.setFontSize(16);
        word1.setFillColor("#00FFFF");
        word1.setStrokeWidth(20);
        word1.setStrokeColor("#0000FF");

        AddTextRequest.WordStyleRequest word2 = new AddTextRequest.WordStyleRequest();
        word2.setStartIndex(3);
        word2.setLength(2);
        word2.setStyleId(296653948753219540L);
        word2.setFontSize(18);

        text1Request.setWordStyles(List.of(word1, word2));

        MvcResult text1Result = mockMvc.perform(post("/api/project/text")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJsonString(text1Request)))
                .andExpect(status().isOk())
                .andReturn();

        BaseResponse<VideoProject> text1Response = JsonUtils.toObject(
                text1Result.getResponse().getContentAsString(),
                new TypeReference<>() {}
        );
        assertEquals(0, text1Response.getCode());
        System.out.println("10. 添加第一个文本成功");

        // 11. 添加第二个文本（使用全局样式和花字）
        AddTextRequest text2Request = new AddTextRequest();
        text2Request.setProjectId(projectId);
        text2Request.setScriptIndex(0);
        text2Request.setText("你真好呀");
        text2Request.setStartTime(2001L);
        text2Request.setDuration(999L);
        text2Request.setLayoutIndex(1000);
        text2Request.setPositionX(0);
        text2Request.setPositionY(-800);
        text2Request.setRotate(0);
        text2Request.setAsSubtitle(true);
        text2Request.setStyleId(296653948753219540L);

        // 添加花字
        AddTextRequest.WordStyleRequest word3 = new AddTextRequest.WordStyleRequest();
        word3.setStartIndex(1);
        word3.setLength(2);
        word3.setFlowerId(270413717936603137L);
        text2Request.setWordStyles(List.of(word3));

        MvcResult text2Result = mockMvc.perform(post("/api/project/text")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJsonString(text2Request)))
                .andExpect(status().isOk())
                .andReturn();

        BaseResponse<VideoProject> text2Response = JsonUtils.toObject(
                text2Result.getResponse().getContentAsString(),
                new TypeReference<>() {}
        );
        assertEquals(0, text2Response.getCode());
        System.out.println("11. 添加第二个文本成功");

        // 12. 添加文本模板
        AddTextTemplateRequest textTemplateRequest = new AddTextTemplateRequest();
        textTemplateRequest.setProjectId(projectId);
        textTemplateRequest.setScriptIndex(0);
        textTemplateRequest.setTemplateId(270414005699805185L);
        textTemplateRequest.setTexts(List.of("非", "常", "棒", "duoec.com"));
        textTemplateRequest.setStartTime(2001L);
        textTemplateRequest.setDuration(999L);
        textTemplateRequest.setLayoutIndex(1000);
        textTemplateRequest.setZoomX(5000);
        textTemplateRequest.setZoomY(5000);
        textTemplateRequest.setPositionX(0);
        textTemplateRequest.setPositionY(1400);

        MvcResult textTemplateResult = mockMvc.perform(post("/api/project/text-template")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJsonString(textTemplateRequest)))
                .andExpect(status().isOk())
                .andReturn();

        BaseResponse<VideoProject> textTemplateResponse = JsonUtils.toObject(
                textTemplateResult.getResponse().getContentAsString(),
                new TypeReference<>() {}
        );
        assertEquals(0, textTemplateResponse.getCode());
        System.out.println("12. 添加文本模板成功");

        // 13. 添加音效
        AddSoundRequest soundRequest = new AddSoundRequest();
        soundRequest.setProjectId(projectId);
        soundRequest.setScriptIndex(0);
        soundRequest.setSoundId(270464042140893185L);
        soundRequest.setStartTime(1000L);
        soundRequest.setDuration(3000L);

        MvcResult soundResult = mockMvc.perform(post("/api/project/sound")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJsonString(soundRequest)))
                .andExpect(status().isOk())
                .andReturn();

        BaseResponse<VideoProject> soundResponse = JsonUtils.toObject(
                soundResult.getResponse().getContentAsString(),
                new TypeReference<>() {}
        );
        assertEquals(0, soundResponse.getCode());
        System.out.println("13. 添加音效成功");

        // 14. 添加贴纸
        AddStickerRequest stickerRequest = new AddStickerRequest();
        stickerRequest.setProjectId(projectId);
        stickerRequest.setScriptIndex(0);
        stickerRequest.setStickerId(270402997699280897L);
        stickerRequest.setStartTime(1500L);
        stickerRequest.setDuration(3000L);
        stickerRequest.setZoomX(5000);
        stickerRequest.setZoomY(5000);
        stickerRequest.setPositionX(500);
        stickerRequest.setPositionY(0);
        stickerRequest.setRotate(-45);

        MvcResult stickerResult = mockMvc.perform(post("/api/project/sticker")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJsonString(stickerRequest)))
                .andExpect(status().isOk())
                .andReturn();

        BaseResponse<VideoProject> stickerResponse = JsonUtils.toObject(
                stickerResult.getResponse().getContentAsString(),
                new TypeReference<>() {}
        );
        assertEquals(0, stickerResponse.getCode());
        System.out.println("14. 添加贴纸成功");

        // 输出最终的视频项目JSON
        VideoProject finalProject = stickerResponse.getData();
        System.out.println("\n=== 最终视频项目JSON ===");
        System.out.println(JsonUtils.toJsonString(finalProject));

        System.out.println("\n✅ 所有API调用成功，视频项目构建完成！");

        JianYingProjectInfo jyProject = new JianyingBuilder().build(finalProject);
    }
}
