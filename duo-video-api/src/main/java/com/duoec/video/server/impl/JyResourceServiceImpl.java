package com.duoec.video.server.impl;

import com.duoec.base.core.util.UrlUtils;
import com.duoec.base.dto.response.BaseResponse;
import com.duoec.video.dto.request.JianYingResourceQuery;
import com.duoec.video.dto.response.JianYingResourceCategoryDto;
import com.duoec.video.dto.response.JianyingResourceDto;
import com.duoec.video.jy.utils.DuoApiUtils;
import com.duoec.video.server.JyResourceService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JyResourceServiceImpl implements JyResourceService {

    @Override
    public BaseResponse<List<JianyingResourceDto>> list(JianYingResourceQuery request) {
        int pageSize = Optional.ofNullable(request.getPageSize()).orElse(20);
        int pageNo = Optional.ofNullable(request.getPageNo()).orElse(1);
        String url = DuoApiUtils.BASE_JY_RESOURCE_API + "list?open=true&isBusiness=true&type=" + request.getType() + "&pageSize=" + pageSize + "&pageNo=" + pageNo;
        String keywords = request.getKeywords();
        if (StringUtils.hasLength(keywords)) {
            url += "&keywords=" + UrlUtils.encode(keywords);
        }
        Integer categoryId = request.getCategoryId();
        if (categoryId != null && categoryId > 0) {
            url += "&categoryId=" + categoryId;
        }
        Integer textCount = request.getTextCount();
        if (textCount != null && textCount > 0) {
            url += "&textCount=" + textCount;
        }
        return DuoApiUtils.get(url, new TypeReference<>() {
        });
    }

    @Override
    public BaseResponse<List<JianYingResourceCategoryDto>> getCategories(String type) {
        String url = DuoApiUtils.BASE_JY_RESOURCE_API + "category/list?type=" + type;
        return DuoApiUtils.get(url, new TypeReference<>() {
        });
    }
}
