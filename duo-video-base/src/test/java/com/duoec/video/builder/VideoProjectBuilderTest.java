package com.duoec.video.builder;

import com.duoec.base.core.util.JsonUtils;
import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.video.project.VideoProject;
import org.junit.jupiter.api.Test;

class VideoProjectBuilderTest {
    @Test
    void createBuilder() {
        VideoProject project = VideoProjectBuilder.createBuilder(SnowflakeIdUtils.nextTmpId(), "测试", 1080, 1920)
                .setTest(true) // 设置为测试模式
                .getTextTemplateBuilder(0) // 进入第一个分镜
                .add(7507280729638817086L, "太好了", 0, 3000) // 添加一个文本模板
                .setPosition(0, -400) // 指定展示位置，0，0表示视频中央 上正下负 左负右正
                .back() // 返回到 VideoProjectBuilder
                .getProject(); // 导出工程

        System.out.println(JsonUtils.toJsonString(project));
    }
}