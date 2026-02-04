package com.duoec.video.controller;

import com.duoec.base.dto.response.BaseResponse;
import com.duoec.video.dto.request.JianYingResourceCategoryQuery;
import com.duoec.video.dto.request.JianYingResourceQuery;
import com.duoec.video.dto.response.JianYingResourceCategoryDto;
import com.duoec.video.dto.response.JianyingResourceDto;
import com.duoec.video.server.JyResourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @chapter 视频
 * @section 视频资源
 */
@RestController
@RequestMapping("/api/video/resource")
@RequiredArgsConstructor
public class VideoResourceApiController {
    private final JyResourceService jyResourceService;

    /**
     * 剪映资源列表
     * @param request 列表请求
     * @return 剪映资源列表
     */
    @GetMapping("/list")
    public BaseResponse<List<JianyingResourceDto>> list(@Valid JianYingResourceQuery request) {
        if (!StringUtils.hasLength(request.getType())) {
            return BaseResponse.error("请选择资源类型：type");
        }
        String keywords = request.getKeywords();
        if (StringUtils.hasLength(keywords) && keywords.length() > 1024) {
            return BaseResponse.error("搜索关键词长度不能超过1024");
        }
        return jyResourceService.list(request);
    }

    /**
     * 剪映资源分类列表
     * @return 剪映资源分类列表
     */
    @GetMapping("/category/list")
    public BaseResponse<List<JianYingResourceCategoryDto>> list(@Valid JianYingResourceCategoryQuery request) {
        if (!StringUtils.hasLength(request.getType())) {
            return BaseResponse.error("请选择资源类型：type");
        }
        return jyResourceService.getCategories(request.getType());
    }
}
