package com.duoec.video.server;

import com.duoec.base.dto.response.BaseResponse;
import com.duoec.video.dto.request.JianYingResourceQuery;
import com.duoec.video.dto.response.JianYingResourceCategoryDto;
import com.duoec.video.dto.response.JianyingResourceDto;
import jakarta.validation.Valid;

import java.util.List;

public interface JyResourceService {
    BaseResponse<List<JianyingResourceDto>> list(@Valid JianYingResourceQuery request);

    BaseResponse<List<JianYingResourceCategoryDto>> getCategories(String type);
}
