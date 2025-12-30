package com.duoec.video.jy;

import com.duoec.base.core.util.FileUtils;
import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.video.builder.ProjectBuilder;
import com.duoec.video.jy.dto.info.JianYingProjectInfo;
import com.duoec.video.jy.utils.JianyingResourceUtils;
import com.duoec.video.project.VideoProject;
import com.duoec.video.project.material.TextStyle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        long textTemplateResourceId = 7507280729638817086L;

        VideoProject videoProject = ProjectBuilder.createBuilder(SnowflakeIdUtils.nextTmpId(), "测试", 1080, 1920)
                .setTest(true) // 设置为测试模式

                .getScriptBuilder(0) // 进入第一个分镜
                .addTextTemplateAndGetBuilder(textTemplateResourceId, "太好了", 0, 3000) // 添加一个文本模板
                .setPosition(0, -400) // 指定展示位置，0，0表示视频中央 上正下负 左负右正
                .back() // 返回到 ProjectScriptBuilder

                .addTextAndGetBuilder(String.valueOf(textTemplateResourceId), 0, 3000)
                .setStyle(
                        new TextStyle()
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
}