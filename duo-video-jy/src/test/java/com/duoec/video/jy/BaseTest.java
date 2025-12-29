package com.duoec.video.jy;

import com.duoec.video.jy.builder.JianyingMaterialBuilder;
import com.duoec.video.jy.service.impl.StorageServiceImpl;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    public static void init() {
        JianyingBuilder.storageService = new StorageServiceImpl();
        JianyingProjectBuildState.DEBUG_JY_DRAFT_DIR = "/Users/xuwenzhen/Movies/JianyingPro/User Data/Projects/com.lveditor.draft/";
    }
}
