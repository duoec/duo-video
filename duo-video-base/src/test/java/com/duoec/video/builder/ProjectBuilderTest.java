package com.duoec.video.builder;

import com.duoec.base.core.util.JsonUtils;
import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.video.project.VideoProject;
import org.junit.jupiter.api.Test;

class ProjectBuilderTest {
    @Test
    void createBuilder() {
        VideoProject project = ProjectBuilder.createBuilder(SnowflakeIdUtils.nextTmpId(), "测试", 1080, 1920)
                .setTest(true) // 设置为测试模式
                .getScriptBuilder(0) // 进入第一个分镜

                .addTextTemplateAndGetBuilder(7507280729638817086L, "太好了", 0, 3000) // 添加一个文本模板
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
}